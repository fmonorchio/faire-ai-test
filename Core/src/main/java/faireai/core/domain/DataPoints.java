package faireai.core.domain;

import faireai.core.util.NumberUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static faireai.core.util.NumberUtils.avg;
import static java.lang.Math.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataPoints implements Serializable {

    public static DataPoints merge(DataPoints first, DataPoints second) {

        return new DataPoints(
                NumberUtils.round(avg(first.avg, second.avg)),
                NumberUtils.round(min(first.min, second.min)),
                NumberUtils.round(max(first.max, second.max))
        );
    }

    private double avg;

    private double min;

    private double max;

    public DataPoints(double value) {
        avg = value;
        min = value;
        max = value;
    }

}
