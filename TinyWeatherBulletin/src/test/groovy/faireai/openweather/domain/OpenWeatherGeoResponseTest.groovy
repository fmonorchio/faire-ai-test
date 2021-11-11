package faireai.openweather.domain

import faireai.core.exception.DuplicateGeoCityException
import faireai.core.exception.EmptyGeoCityException
import spock.lang.Specification
import spock.lang.Subject

class OpenWeatherGeoResponseTest extends Specification {

    @Subject
    OpenWeatherGeoResponse response

    def 'Throw exception if no city is available'() {

        given:
        response = new OpenWeatherGeoResponse([
        ])

        when:
        response.getCityOrThrow()

        then:
        thrown(EmptyGeoCityException)
    }

    def 'Throw exception if multiple city are available'() {

        given:
        response = new OpenWeatherGeoResponse([
                new CityGeoData('Milan', 'IT', 10, 15),
                new CityGeoData('Turin', 'IT', 15, 10)
        ])

        when:
        response.getCityOrThrow()

        then:
        thrown(DuplicateGeoCityException)
    }

    def 'Get the only available city'() {

        given:
        def city = new CityGeoData('Milan', 'IT', 10, 15)
        response = new OpenWeatherGeoResponse([ city ])

        expect:
        response.getCityOrThrow() == city
    }

}
