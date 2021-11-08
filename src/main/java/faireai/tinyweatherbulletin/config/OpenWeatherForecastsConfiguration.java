package faireai.tinyweatherbulletin.config;

import lombok.Data;

import java.util.List;

@Data
public class OpenWeatherForecastsConfiguration {

    private List<String> exclusions;

    private String units;

}
