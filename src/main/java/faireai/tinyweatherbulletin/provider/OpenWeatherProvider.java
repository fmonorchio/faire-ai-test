package faireai.tinyweatherbulletin.provider;

import faireai.tinyweatherbulletin.annotation.Provider;
import faireai.tinyweatherbulletin.config.OpenWeatherConfiguration;
import faireai.tinyweatherbulletin.config.OpenWeatherForecastsConfiguration;
import faireai.tinyweatherbulletin.config.OpenWeatherSecurityConfiguration;
import faireai.tinyweatherbulletin.domain.openweather.forecasts.OpenWeatherForecastsResponse;
import faireai.tinyweatherbulletin.domain.openweather.geo.OpenWeatherGeoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Provider
public class OpenWeatherProvider implements WeatherProvider {

    @Autowired
    private OpenWeatherConfiguration openWeatherConfiguration;

    @Autowired
    private RestTemplate client;

    @Override
    public OpenWeatherGeoResponse getGeoByCityName(String cityName) {

        String url = String.format("%s/geo/1.0/direct?q={q}&appid={appid}",
                openWeatherConfiguration.getBaseUrl()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", List.of("application/json"));

        OpenWeatherSecurityConfiguration security = openWeatherConfiguration.getSecurity();
        Map<String, String> params = Map.of(
                "q", cityName,
                "appid", security.getApiKey()
        );

        HttpEntity<?> entity = new HttpEntity<>(headers);

        return client.exchange(url, HttpMethod.GET, entity, OpenWeatherGeoResponse.class, params)
                .getBody();
    }

    @Override
    public OpenWeatherForecastsResponse getForecastsByGeo(double latitude, double longitude) {

        String url = String.format("%s/data/2.5/onecall?lat={lat}&lon={lon}&exclude={exclude}&appid={appid}&units={units}",
                openWeatherConfiguration.getBaseUrl()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", List.of("application/json"));

        HttpEntity<?> entity = new HttpEntity<>(headers);

        OpenWeatherSecurityConfiguration security = openWeatherConfiguration.getSecurity();
        OpenWeatherForecastsConfiguration forecasts = openWeatherConfiguration.getForecasts();
        Map<String, String> params = Map.of(
                "lat", String.valueOf(latitude),
                "lon", String.valueOf(longitude),
                "exclude", String.join(",", forecasts.getExclusions()),
                "appid", security.getApiKey(),
                "units", forecasts.getUnits()
        );

        return client.exchange(url, HttpMethod.GET, entity, OpenWeatherForecastsResponse.class, params)
                .getBody();
    }

}
