package faireai.tinyweatherbulletin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherForecasts {

    private Map<Instant, WeatherAggregation> work;

    private Map<Instant, WeatherAggregation> vacation;

}
