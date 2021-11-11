package faireai.openweather.provider;

import faireai.core.annotation.Provider;
import faireai.core.domain.GeoCoordinates;
import faireai.core.domain.Measures;
import faireai.core.provider.WeatherProvider;
import faireai.openweather.config.ForecastsConfiguration;
import faireai.openweather.config.OpenWeatherConfiguration;
import faireai.openweather.config.SecurityConfiguration;
import faireai.openweather.domain.CityGeoData;
import faireai.openweather.domain.HourlyForecast;
import faireai.openweather.domain.OpenWeatherForecastsResponse;
import faireai.openweather.domain.OpenWeatherGeoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Provider
public class OpenWeatherProvider implements WeatherProvider {

    @Autowired
    private OpenWeatherConfiguration config;

    @Autowired
    private RestTemplate client;

    @Autowired
    private ConversionService conversionService;

    @Override
    public GeoCoordinates getGeoByCityName(String cityName, String countryCode) {

        String url = String.format("%s/geo/1.0/direct?q={q}&appid={appid}",
                config.getBaseUrl()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", List.of("application/json"));

        HttpEntity<?> entity = new HttpEntity<>(headers);

        String query = String.format("%s%s",
                cityName,
                Objects.isNull(countryCode) ? "" : "," + countryCode
                );

        SecurityConfiguration security = config.getSecurity();
        Map<String, String> params = Map.of(
                "q", query,
                "appid", security.getApiKey()
        );

        OpenWeatherGeoResponse body = client.exchange(url, HttpMethod.GET, entity, OpenWeatherGeoResponse.class, params)
                .getBody();

        CityGeoData city = Objects.requireNonNull(body).getCityOrThrow();
        return conversionService.convert(city, GeoCoordinates.class);
    }

    @Override
    public List<Measures> getForecastsByGeo(double latitude, double longitude) {

        String url = String.format("%s/data/2.5/onecall?lat={lat}&lon={lon}&exclude={exclude}&appid={appid}&units={units}",
                config.getBaseUrl()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", List.of("application/json"));

        HttpEntity<?> entity = new HttpEntity<>(headers);

        SecurityConfiguration security = config.getSecurity();
        ForecastsConfiguration forecasts = config.getForecasts();
        Map<String, String> params = Map.of(
                "lat", String.valueOf(latitude),
                "lon", String.valueOf(longitude),
                "exclude", String.join(",", forecasts.getExclusions()),
                "appid", security.getApiKey(),
                "units", forecasts.getUnits()
        );

        OpenWeatherForecastsResponse body = client.exchange(url, HttpMethod.GET, entity, OpenWeatherForecastsResponse.class, params)
                .getBody();

        List<HourlyForecast> hourlyForecasts = Objects.requireNonNull(body).getHourly();
        return hourlyForecasts.stream()
                .map(hf -> conversionService.convert(hf, Measures.class))
                .collect(Collectors.toList());
    }

}
