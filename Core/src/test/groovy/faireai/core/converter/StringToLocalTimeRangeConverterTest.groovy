package faireai.core.converter

import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalTime
import java.time.format.DateTimeParseException

class StringToLocalTimeRangeConverterTest extends Specification {

    @Subject
    StringToLocalTimeRangeConverter converter

    def 'Range is converted correctly'() {

        given:
        converter = new StringToLocalTimeRangeConverter()

        when:
        def range = converter.convert('19:00-20:00')

        then:
        with(range) {
            verifyAll {
                minimum == LocalTime.of(19, 0)
                maximum == LocalTime.of(20, 0)
            }
        }
        range.contains(LocalTime.of(19, 30))
    }

    def 'Range failed to convert 1'() {

        given:
        converter = new StringToLocalTimeRangeConverter()

        when:
        converter.convert('19')

        then:
        thrown(DateTimeParseException)
    }

    def 'Range failed to convert 2'() {

        given:
        converter = new StringToLocalTimeRangeConverter()

        when:
        converter.convert('19-20')

        then:
        thrown(DateTimeParseException)
    }

}
