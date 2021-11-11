package faireai.core.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Forecasts implements Serializable {

    @Schema(description = "Forecasts for the working hours")
    private List<Measures> work;

    @Schema(description = "Forecasts for the vacation hours")
    private List<Measures> vacation;

}
