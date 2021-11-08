package faireai.tinyweatherbulletin.domain.openweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Hourly implements Serializable {

    public int dt;

    public double temp;

    @JsonProperty("feels_like")
    public double feelsLike;

    public int pressure;

    public int humidity;

    @JsonProperty("dew_point")
    public double dewPoint;

    public double uvi;

    public int clouds;

    public int visibility;

    @JsonProperty("wind_speed")
    public double windSpeed;

    @JsonProperty("wind_deg")
    public int windDeg;

    @JsonProperty("wind_gust")
    public double windGust;

    public List<Weather> weather;

    public int pop;

}
