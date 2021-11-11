package faireai.openweather.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HourlyForecast implements Serializable {

    @JsonProperty("dt")
    private Instant date;

    @JsonProperty("temp")
    private double temperature;

    private double humidity;

}
