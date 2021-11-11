package faireai.core.domain

import spock.lang.Specification
import spock.lang.Subject

import java.time.Instant

class MeasuresTest extends Specification {

    @Subject
    Measures measures

    def 'Measures must be compared by date'() {

        given:
        measures = new Measures(
                Instant.now(),
                null,
                null
        )

        and:
        def other = new Measures(
                Instant.EPOCH,
                null,
                null
        )

        when:
        def compare = measures <=> other

        then:
        compare == 1
    }

}
