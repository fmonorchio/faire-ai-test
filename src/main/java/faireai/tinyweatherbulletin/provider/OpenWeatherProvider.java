package faireai.tinyweatherbulletin.provider;

import faireai.tinyweatherbulletin.annotation.Provider;
import faireai.tinyweatherbulletin.config.OpenWeatherConfiguration;
import faireai.tinyweatherbulletin.config.OpenWeatherForecastsConfiguration;
import faireai.tinyweatherbulletin.config.OpenWeatherSecurityConfiguration;
import faireai.tinyweatherbulletin.domain.openweather.OpenWeatherForecastsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
    public Object getGeoByCityName(String cityName) {

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
        ResponseEntity<OpenWeatherForecastsResponse> response = client.exchange(url, HttpMethod.GET, entity, OpenWeatherForecastsResponse.class, params);

        return response;
    }

    @Override
    public OpenWeatherForecastsResponse getWeatherByCity(String cityName) {

        //getGeoByCityName(cityName);

        String url = String.format("%s/data/2.5/onecall?lat={lat}&lon={lon}&exclude={exclude}&appid={appId}&units={units}",
                openWeatherConfiguration.getBaseUrl()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", List.of("application/json"));

        HttpEntity<?> entity = new HttpEntity<>(headers);

        OpenWeatherSecurityConfiguration security = openWeatherConfiguration.getSecurity();
        OpenWeatherForecastsConfiguration forecasts = openWeatherConfiguration.getForecasts();
        Map<String, String> params = Map.of(
                "lat", "39.082520", //cambia l'input utente
                "lon", "-94.582306", //cambia l'input utente
                "exclude", String.join(",", forecasts.getExclusions()),
                "appId", security.getApiKey(),
                "units", forecasts.getUnits()
        );

        ResponseEntity<OpenWeatherForecastsResponse> response = client.exchange(url, HttpMethod.GET, entity, OpenWeatherForecastsResponse.class, params);

        return response.getBody();
    }

}
