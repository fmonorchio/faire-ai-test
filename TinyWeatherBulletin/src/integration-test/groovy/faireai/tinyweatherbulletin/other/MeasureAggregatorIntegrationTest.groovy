package faireai.tinyweatherbulletin.other

import faireai.core.domain.DataPoints
import faireai.core.domain.Measures
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

import static java.time.ZoneOffset.UTC

@SpringBootTest
@ActiveProfiles('test')
class MeasureAggregatorIntegrationTest extends Specification {

    @Subject
    @Autowired
    MeasureAggregator aggregator

    def 'Aggregation works correctly'() {

        given:
        def forecasts = [
                new Measures(
                        LocalDateTime.of(2021, 11, 11, 7, 00).toInstant(UTC),
                        new DataPoints(10),
                        new DataPoints(80)
                ),
                new Measures(
                        LocalDateTime.of(2021, 11, 11, 8, 00).toInstant(UTC),
                        new DataPoints(10),
                        new DataPoints(80)
                ),
                new Measures(
                        LocalDateTime.of(2021, 11, 11, 9, 00).toInstant(UTC),
                        new DataPoints(10),
                        new DataPoints(80)
                ),
                new Measures(
                        LocalDateTime.of(2021, 11, 11, 10, 00).toInstant(UTC),
                        new DataPoints(20),
                        new DataPoints(90)
                ),
                new Measures(
                        LocalDateTime.of(2021, 11, 11, 11, 00).toInstant(UTC),
                        new DataPoints(20),
                        new DataPoints(90)
                )
        ]

        when:
        def aggregated = aggregator.aggregate(forecasts)

        then:
        with(aggregated) {
            work.size() == 2
            vacation.size() == 1
        }
    }
}
