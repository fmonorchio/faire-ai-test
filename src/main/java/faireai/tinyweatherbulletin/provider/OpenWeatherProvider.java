package faireai.tinyweatherbulletin.provider;

import faireai.tinyweatherbulletin.annotation.Provider;
import faireai.tinyweatherbulletin.config.OpenWeatherConfiguration;
import faireai.tinyweatherbulletin.config.OpenWeatherForecastsConfiguration;
import faireai.tinyweatherbulletin.config.OpenWeatherSecurityConfiguration;
import faireai.tinyweatherbulletin.domain.openweather.CityGeoData;
import faireai.tinyweatherbulletin.domain.openweather.OpenWeatherForecastsResponse;
import faireai.tinyweatherbulletin.domain.openweather.OpenWeatherGeoCityResponse;
import faireai.tinyweatherbulletin.exception.DuplicateGeoCityException;
import faireai.tinyweatherbulletin.exception.EmptyGeoCityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Provider
public class OpenWeatherProvider implements WeatherProvider {

    @Autowired
    OpenWeatherConfiguration openWeatherConfiguration;

    @Autowired
    private RestTemplate client;

    @Override
    public OpenWeatherGeoCityResponse getGeoByCityName(String cityName) {

        String url = String.format("%s/geo/1.0/direct?q={cityName}&appid={appId}",
                openWeatherConfiguration.getBaseUrl()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", List.of("application/json"));

        OpenWeatherSecurityConfiguration security = openWeatherConfiguration.getSecurity();
        Map<String, String> params = Map.of(
                "cityName", cityName,
                "appId", security.getApiKey()
        );

        HttpEntity<?> entity = new HttpEntity<>(headers);
        List<CityGeoData> cities = client.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<CityGeoData>>() {}, params)
                .getBody();

        return new OpenWeatherGeoCityResponse(cities);
    }

    @Override
    public OpenWeatherForecastsResponse getWeatherByCity(String cityName) {

        OpenWeatherGeoCityResponse geo = getGeoByCityName(cityName);
        List<CityGeoData> cities = geo.getCities();

        if (cities.isEmpty()) {
            throw new EmptyGeoCityException();
        }

        if (cities.size() > 1) {
            throw new DuplicateGeoCityException();
        }

        CityGeoData cityGeo = cities.get(0);

        String url = String.format("%s/data/2.5/onecall?lat={lat}&lon={lon}&exclude={exclude}&appid={appId}&units={units}",
                openWeatherConfiguration.getBaseUrl()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", List.of("application/json"));

        HttpEntity<?> entity = new HttpEntity<>(headers);

        OpenWeatherSecurityConfiguration security = openWeatherConfiguration.getSecurity();
        OpenWeatherForecastsConfiguration forecasts = openWeatherConfiguration.getForecasts();
        Map<String, String> params = Map.of(
                "lat", String.valueOf(cityGeo.getLat()),
                "lon", String.valueOf(cityGeo.getLon()),
                "exclude", String.join(",", forecasts.getExclusions()),
                "appId", security.getApiKey(),
                "units", forecasts.getUnits()
        );

        return client.exchange(url, HttpMethod.GET, entity, OpenWeatherForecastsResponse.class, params)
                .getBody();
    }

}
