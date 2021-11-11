package faireai.openweather.converter

import faireai.core.domain.DataPoints
import faireai.core.domain.Measures
import faireai.openweather.domain.HourlyForecast
import spock.lang.Specification
import spock.lang.Subject

import java.time.Instant

class HourlyForecastToMeasuresConverterTest extends Specification {

    @Subject
    HourlyForecastToMeasuresConverter converter

    def 'Conversion works correctly'() {

        given:
        converter = new HourlyForecastToMeasuresConverter()

        and:
        def date = Instant.EPOCH
        def temperature = 18.35
        def humidity = 80
        def source = new HourlyForecast(date, temperature, humidity)

        when:
        def target = converter.convert(source)

        then:
        target == new Measures(
                date,
                new DataPoints(temperature),
                new DataPoints(humidity)
        )
    }

}
