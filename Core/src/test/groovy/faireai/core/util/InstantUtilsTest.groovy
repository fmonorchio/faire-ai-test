package faireai.core.util

import spock.lang.Specification

import java.time.LocalDateTime

import static java.time.ZoneOffset.UTC

class InstantUtilsTest extends Specification {

    def 'Median is calculated correctly'() {

        given:
        def first = LocalDateTime.of(2021, 11, 11, 11, 30).toInstant(UTC)
        def second = LocalDateTime.of(2021, 11, 11, 12, 00).toInstant(UTC)
        def median = InstantUtils.median(first, second)

        expect:
        median == LocalDateTime.of(2021, 11, 11, 11, 45).toInstant(UTC)
    }

}
