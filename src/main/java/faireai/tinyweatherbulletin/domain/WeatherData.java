package faireai.tinyweatherbulletin.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Data
@NoArgsConstructor
public class WeatherData {

    private double avg;

    private double min;

    private double max;

    public WeatherData(double value) {
        avg = value;
        min = value;
        max = value;
    }

    public void aggregate(WeatherData data) {

        avg = (avg + data.avg) / 2;
        min = min(min, data.min);
        max = max(max, data.max);
    }

}
