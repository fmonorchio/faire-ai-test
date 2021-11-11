package faireai.openweather.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityGeoData implements Serializable {

    @JsonProperty("name")
    private String city;

    private String country;

    @JsonProperty("lat")
    private double latitude;

    @JsonProperty("lon")
    private double longitude;

}
