package faireai.core.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather implements Serializable {

    @Schema(description = "City to search for")
    private String city;

    @Schema(description = "Country of the city. Useful when multiple city with same name exists")
    private String country;

    @Schema(description = "Latitude of the city")
    private double latitude;

    @Schema(description = "Longitude of the city")
    private double longitude;

    @Schema(description = "Weather forecasts for the next 48 hours")
    private Forecasts forecasts;

}
