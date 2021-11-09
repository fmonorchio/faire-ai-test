package faireai.tinyweatherbulletin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;

@Data
@NoArgsConstructor
public class WeatherAggregation {

    private WeatherData temperature;

    private WeatherData humidity;

    public WeatherAggregation(double temp, double hum) {
        temperature = new WeatherData(temp);
        humidity = new WeatherData(hum);
    }

    @JsonIgnore
    public boolean isEmpty() {
        return isNull(temperature) && isNull(humidity);
    }

    public static WeatherAggregation aggregate(WeatherAggregation wa1, WeatherAggregation wa2) {

        if (wa1.isEmpty()) {
            return wa2;
        }

        wa1.getTemperature().aggregate(wa2.getTemperature());
        wa1.getHumidity().aggregate(wa2.getHumidity());

        return wa1;
    }

}
