package faireai.core.provider;

import faireai.core.domain.GeoCoordinates;
import faireai.core.domain.Measures;

import java.util.List;

public interface WeatherProvider {

    GeoCoordinates getGeoByCityName(String cityName);

    List<Measures> getForecastsByGeo(double latitude, double longitude);

}
