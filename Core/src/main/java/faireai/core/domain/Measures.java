package faireai.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Function;

import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoUnit.HOURS;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Measures implements Serializable {

    public static Function<Measures, Instant> hourlyInterval(int interval) {

        return hf -> {

            LocalDateTime date = LocalDateTime.ofInstant(hf.date, UTC);
            int hour = date.getHour();
            return date.truncatedTo(HOURS)
                    .withHour(hour - (hour % interval))
                    .toInstant(UTC);
        };
    }

    public static Measures merge(Measures first, Measures second) {

        if (first.isInvalid()) {
            return second;
        }

        long diff = HOURS.between(first.date, second.date);
        return new Measures(
                first.date.plus(diff / 2, HOURS),
                DataPoints.merge(first.temperature, second.temperature),
                DataPoints.merge(second.humidity, second.humidity)
        );
    }

    private Instant date;

    private DataPoints temperature;

    private DataPoints humidity;

    @JsonIgnore
    public boolean isInvalid() {
        return Objects.isNull(temperature) &&
                Objects.isNull(humidity);
    }

}
