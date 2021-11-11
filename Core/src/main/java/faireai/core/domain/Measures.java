package faireai.core.domain;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Date when the measure occured")
    private Instant date;

    @Schema(description = "Temperature detected")
    private DataPoints temperature;

    @Schema(description = "Humidity detected")
    private DataPoints humidity;

    @Override
    public int compareTo(Measures other) {
        return date.compareTo(other.date);
    }

}
