package faireai.openweather.converter

import faireai.core.domain.GeoCoordinates
import faireai.openweather.domain.CityGeoData
import spock.lang.Specification
import spock.lang.Subject

class CityGeoDataToGeoCoordinatesConverterTest extends Specification {

    @Subject
    CityGeoDataToGeoCoordinatesConverter converter

    def 'Conversion works correctly'() {

        given:
        converter = new CityGeoDataToGeoCoordinatesConverter()

        and:
        def latitude = 13.12345
        def longitude = 50.54321
        def source = new CityGeoData('Reggio Calabria', 'IT',
                latitude,
                longitude
        )

        when:
        def target = converter.convert(source)

        then:
        target == new GeoCoordinates(
                latitude,
                longitude
        )
    }

}
