package faireai.tinyweatherbulletin.other;

import faireai.tinyweatherbulletin.config.ApplicationConfiguration;
import faireai.core.enumeration.HourType;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.Map;

import static java.util.Objects.isNull;

@Component
public class HourTypeSelector {

    @Autowired
    private ApplicationConfiguration configuration;

    public HourType getHourType(Instant instant) {

        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        DayOfWeek day = dateTime.getDayOfWeek();

        Map<DayOfWeek, Range<LocalTime>> workingHours = configuration.getWorkingHours();

        Range<LocalTime> range = workingHours.get(day); //"09:00-18:00"
        if (isNull(range)) {
            return HourType.VACATION;
        }

        LocalTime time = dateTime.toLocalTime();
        if (range.contains(time)) {
            return HourType.WORK;
        }
        return HourType.VACATION;
    }

}
