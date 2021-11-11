package faireai.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Measures implements Comparable<Measures>, Serializable {

    public static Measures merge(Measures first, Measures second) {

        return new Measures(
                first.date,
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
