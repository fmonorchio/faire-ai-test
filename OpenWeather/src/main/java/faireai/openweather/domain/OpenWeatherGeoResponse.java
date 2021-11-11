package faireai.openweather.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import faireai.core.exception.DuplicateGeoCityException;
import faireai.core.exception.EmptyGeoCityException;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

//TODO: Missing tests
@Data
public class OpenWeatherGeoResponse implements Serializable {

    private List<CityGeoData> cities;

    @JsonCreator
    public OpenWeatherGeoResponse(List<CityGeoData> cities) {
        this.cities = cities;
    }

    @JsonIgnore
    public CityGeoData getCityOrThrow() {

        if (cities.isEmpty()) {
            throw new EmptyGeoCityException();
        }

        if (cities.size() > 1) {
            throw new DuplicateGeoCityException();
        }

        return cities.get(0);
    }

}
