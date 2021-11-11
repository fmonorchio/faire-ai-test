package faireai.core.converter;

import org.apache.commons.lang3.Range;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

//TODO: Missing tests
@Component
public class StringToLocalTimeRangeConverter implements Converter<String, Range<LocalTime>> {

    @Override
    public Range<LocalTime> convert(String source) {

        String[] times = source.split("\\s*-\\s*");

        if (times.length != 2) {
            throw new IllegalArgumentException("Is not a range");
        }

        LocalTime from = LocalTime.parse(times[0]);
        LocalTime to = LocalTime.parse(times[1]);

        return Range.between(from, to);
    }

}
