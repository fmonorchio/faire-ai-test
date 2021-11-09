package faireai.tinyweatherbulletin.domain.openweather;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class HourlyForecast implements Serializable {

    public Instant dt;

    public double temp;

    public int humidity;

}
