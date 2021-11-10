package faireai.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Forecasts implements Serializable {

    private Map<Instant, Measures> work;

    private Map<Instant, Measures> vacation;

}
