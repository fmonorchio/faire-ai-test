package faireai.openweather.converter;

import faireai.core.domain.GeoCoordinates;
import faireai.openweather.domain.CityGeoData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CityGeoDataToGeoCoordinatesConverter implements Converter<CityGeoData, GeoCoordinates> {

    @Override
    public GeoCoordinates convert(CityGeoData source) {

        return new GeoCoordinates(
                source.getLatitude(),
                source.getLongitude()
        );
    }

}
