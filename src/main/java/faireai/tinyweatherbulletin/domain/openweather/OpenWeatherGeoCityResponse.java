package faireai.tinyweatherbulletin.domain.openweather;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class OpenWeatherGeoCityResponse implements Serializable {

    List<CityGeoData> cities;

}
