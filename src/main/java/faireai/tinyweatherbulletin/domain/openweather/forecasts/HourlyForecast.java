package faireai.tinyweatherbulletin.domain.openweather.forecasts;

import com.fasterxml.jackson.annotation.JsonProperty;
import faireai.tinyweatherbulletin.domain.WeatherAggregation;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;

@Data
public class HourlyForecast implements Serializable {

    @JsonProperty("dt")
    private Instant date;

    @JsonProperty("temp")
    private double temperature;

    private double humidity;

    public WeatherAggregation toWeatherAggregation() {
        return new WeatherAggregation(temperature, humidity);
    }

    public static Function<HourlyForecast, Instant> hourlyInterval(int interval) {

        return hf -> {

            LocalDateTime date = LocalDateTime.ofInstant(hf.date, ZoneOffset.UTC);
            int hour = date.getHour();
            return date.truncatedTo(ChronoUnit.HOURS).withHour(hour - (hour % interval)).toInstant(ZoneOffset.UTC);
        };
    }

}
