package faireai.tinyweatherbulletin.config;

import lombok.Data;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "application")
public class ApplicationConfiguration {

    @Autowired
    private OpenWeatherConfiguration openWeather;

    Map<DayOfWeek, Range<LocalTime>> workingHours;

}
