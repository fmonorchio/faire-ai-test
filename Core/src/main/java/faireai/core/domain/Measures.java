package faireai.core.domain;

import faireai.core.util.InstantUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.function.Function;

import static java.time.ZoneOffset.UTC;

//TODO: Missing tests
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Measures implements Comparable<Measures>, Serializable {

    public static Function<Measures, String> hourlyInterval(int interval) {

        return hf -> {

            LocalDateTime date = LocalDateTime.ofInstant(hf.date, UTC);
            int hour = date.getHour();

            return String.format("%4d%02d%02d%02d",
                    date.getYear(),
                    date.getMonthValue(),
                    date.getDayOfMonth(),
                    hour - (hour % interval)
            );
        };
    }

    public static Measures merge(Measures first, Measures second) {

        return new Measures(
                InstantUtils.median(first.date, second.date),
                DataPoints.merge(first.temperature, second.temperature),
                DataPoints.merge(first.humidity, second.humidity)
        );
    }

    private Instant date;

    private DataPoints temperature;

    private DataPoints humidity;

    @Override
    public int compareTo(Measures other) {
        return date.compareTo(other.date);
    }

}
