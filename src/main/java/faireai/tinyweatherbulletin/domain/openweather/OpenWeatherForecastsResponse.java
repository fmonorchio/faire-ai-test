package faireai.tinyweatherbulletin.domain.openweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OpenWeatherForecastsResponse implements Serializable {

    public double lat;

    public double lon;

    public String timezone;

    @JsonProperty("timezone_offset")
    public int timezoneOffset;

    public List<Hourly> hourly;

}
