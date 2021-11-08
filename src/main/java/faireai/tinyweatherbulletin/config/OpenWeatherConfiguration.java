package faireai.tinyweatherbulletin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "application.providers.openweather")
public class OpenWeatherConfiguration {

    @NestedConfigurationProperty
    private OpenWeatherSecurityConfiguration security;

    @NestedConfigurationProperty
    private OpenWeatherForecastsConfiguration forecasts;

    private String baseUrl;

}
