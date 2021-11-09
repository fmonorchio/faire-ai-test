package faireai.tinyweatherbulletin.domain.openweather;

import lombok.Data;

@Data
public class CityGeoData {

    public String name;

    public double lat;

    public double lon;

    public String country;

    public String state;

}
