package faireai.tinyweatherbulletin.other;

import faireai.core.enumeration.HourType;
import faireai.tinyweatherbulletin.config.ApplicationConfiguration;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.time.ZoneOffset.UTC;
import static java.util.Objects.isNull;

//TODO: Missing tests
@Component
public class HourTypeSelector {

    @Autowired
    private ApplicationConfiguration configuration;

    public HourType getHourType(Instant instant) {

        DayOfWeek day = LocalDateTime.ofInstant(instant, UTC).getDayOfWeek();
        Range<LocalTime> range = configuration.getWorkingHours().get(day);
        if (isNull(range)) {
            return HourType.VACATION;
        }

        LocalTime time = LocalTime.ofInstant(instant, UTC);
        if (range.contains(time)) {
            return HourType.WORK;
        }
        return HourType.VACATION;
    }

}
