package faireai.openweather.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "openweather")
public class OpenWeatherConfiguration {

    @NestedConfigurationProperty
    private SecurityConfiguration security;

    @NestedConfigurationProperty
    private ForecastsConfiguration forecasts;

    private String baseUrl;

}
