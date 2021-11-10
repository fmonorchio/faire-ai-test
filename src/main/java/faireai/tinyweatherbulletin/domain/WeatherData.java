package faireai.tinyweatherbulletin.domain;

import faireai.tinyweatherbulletin.util.NumberUtils;
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

        avg = NumberUtils.round((avg + data.avg) / 2, 2);
        min = NumberUtils.round(min(min, data.min), 2);
        max = NumberUtils.round(max(max, data.max), 2);
    }

}
