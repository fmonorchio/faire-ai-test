package faireai.core.domain;

import faireai.core.util.NumberUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static faireai.core.util.NumberUtils.avg;
import static java.lang.Math.max;
import static java.lang.Math.min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataPoints implements Serializable {

    public static DataPoints merge(DataPoints first, DataPoints second) {

        return new DataPoints(
                NumberUtils.round(avg(first.avg, second.avg), 2),
                NumberUtils.round(min(first.min, second.min), 2),
                NumberUtils.round(max(first.max, second.max), 2)
        );
    }

    @Schema(description = "Average")
    private double avg;

    @Schema(description = "Minimum")
    private double min;

    @Schema(description = "Maximum")
    private double max;

    public DataPoints(double value) {
        avg = value;
        min = value;
        max = value;
    }

}
