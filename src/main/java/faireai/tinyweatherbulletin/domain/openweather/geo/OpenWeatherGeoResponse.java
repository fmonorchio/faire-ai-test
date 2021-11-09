package faireai.tinyweatherbulletin.domain.openweather.geo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import faireai.tinyweatherbulletin.exception.DuplicateGeoCityException;
import faireai.tinyweatherbulletin.exception.EmptyGeoCityException;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OpenWeatherGeoResponse implements Serializable {

    private List<GeoCityData> cities;

    @JsonCreator
    public OpenWeatherGeoResponse(List<GeoCityData> cities) {
        this.cities = cities;
    }

    @JsonIgnore
    public GeoCityData getCityOrThrow() {

        if (cities.isEmpty()) {
            throw new EmptyGeoCityException();
        }

        if (cities.size() > 1) {
            throw new DuplicateGeoCityException();
        }

        return cities.get(0);
    }

}
