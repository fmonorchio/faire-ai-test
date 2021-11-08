package faireai.tinyweatherbulletin.provider;

import faireai.tinyweatherbulletin.annotation.Provider;
import faireai.tinyweatherbulletin.domain.openweather.OpenWeatherResponse;
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
    private RestTemplate client;

    @Override
    public OpenWeatherResponse getWeatherByCity(String cityName) {

        String url = "https://api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}&exclude={exclude}&appid={appid}&units={units}";

        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", List.of("application/json"));

        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<OpenWeatherResponse> response = client.exchange(url, HttpMethod.GET, entity, OpenWeatherResponse.class, Map.of(
                "lat", "39.082520",
                "lon", "-94.582306",
                "exclude", "current,minutely,daily,alerts",
                "appid", "481d417514a22de2a06e9982a4ce9e16",
                "units", "metric"
        ));

        return response.getBody();
    }

}
