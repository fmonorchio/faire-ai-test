package faireai.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Forecasts implements Serializable {

    private List<Measures> work;

    private List<Measures> vacation;

}
