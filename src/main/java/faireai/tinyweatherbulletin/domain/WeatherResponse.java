package faireai.tinyweatherbulletin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherResponse {

    private double latitude;

    private double longitude;

    private WeatherForecasts forecasts;

}
