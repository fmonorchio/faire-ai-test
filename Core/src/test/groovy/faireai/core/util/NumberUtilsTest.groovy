package faireai.core.util

import spock.lang.Specification

class NumberUtilsTest extends Specification {

    def 'Average is calculated correctly'() {

        given:
        def avg = NumberUtils.avg(10, 20, 3)

        expect:
        avg == 11
    }

    def 'Rounding works correctly'() {

        given:
        def round = NumberUtils.round(10.125, 2)

        expect:
        round == 10.13d
    }

}
