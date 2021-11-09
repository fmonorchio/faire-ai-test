package faireai.tinyweatherbulletin.domain.openweather;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OpenWeatherForecastsResponse implements Serializable {

    public double lat;

    public double lon;

    public List<HourlyForecast> hourly;

}
