package faireai.openweather.config;

import lombok.Data;

import java.util.List;

@Data
public class ForecastsConfiguration {

    private List<String> exclusions;

    private String units;

}
