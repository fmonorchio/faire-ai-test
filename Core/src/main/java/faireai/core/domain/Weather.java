package faireai.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather implements Serializable {

    private String city;

    private String country;

    private double latitude;

    private double longitude;

    private Forecasts forecasts;

}
