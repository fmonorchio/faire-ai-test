package faireai.tinyweatherbulletin.domain.openweather.geo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class GeoCityData implements Serializable {

    @JsonProperty("name")
    private String city;

    private String country;

    private String state;

    @JsonProperty("lat")
    private double latitude;

    @JsonProperty("lon")
    private double longitude;

}
