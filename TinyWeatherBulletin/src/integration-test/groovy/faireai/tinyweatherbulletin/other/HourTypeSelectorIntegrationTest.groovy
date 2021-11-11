package faireai.tinyweatherbulletin.other

import faireai.core.enumeration.HourType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

import static java.time.ZoneOffset.UTC

@SpringBootTest
@ActiveProfiles('test')
class HourTypeSelectorIntegrationTest extends Specification {

    @Subject
    @Autowired
    HourTypeSelector selector

    def 'Is a working hour'() {

        given: 'a monday at 10:30'
        def instant = LocalDateTime.of(2021, 11, 8, 10, 30).toInstant(UTC)

        when:
        def type = selector.getHourType(instant)

        then:
        type == HourType.WORK
    }

    def 'Is a vacation hour 1'() {

        given: 'a monday at 08:30'
        def instant = LocalDateTime.of(2021, 11, 8, 8, 30).toInstant(UTC)

        when:
        def type = selector.getHourType(instant)

        then:
        type == HourType.VACATION
    }

    def 'Is a vacation hour 2'() {

        given: 'a sunday at 10:30'
        def instant = LocalDateTime.of(2021, 10, 30, 10, 30).toInstant(UTC)

        when:
        def type = selector.getHourType(instant)

        then:
        type == HourType.VACATION
    }

}
