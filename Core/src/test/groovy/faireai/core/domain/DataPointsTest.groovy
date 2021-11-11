package faireai.core.domain

import spock.lang.Specification

class DataPointsTest extends Specification {

    def 'Merging of "DataPoints" works as expected'() {

        given:
        def first = new DataPoints(18)
        def second = new DataPoints(30)
        def merge = DataPoints.merge(first, second)

        expect:
        with(merge) {
            verifyAll {
                avg == 24
                min == 18
                max == 30
            }
        }
    }

}
