package faireai.tinyweatherbulletin.controller;

import faireai.core.domain.Weather;
import faireai.core.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Slf4j
@Validated
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    @Operation(
            description = "Search the weather (temperature and humidity) for the next two days in any city",
            parameters = {
                    @Parameter(name = "cityName", required = true, description = "Name of the city"),
                    @Parameter(name = "countryCode", description = "Country code of the city. Useful when multiple cities exists with the same name")
            })
    public Weather getWeatherByCity(
            @NotEmpty @Size(min = 3) @RequestParam String cityName,
            @RequestParam(required = false) String countryCode) {

        log.info("Received request for weather forecasts (city: {}, country: {})", cityName, countryCode);
        return weatherService.getWeatherByCity(cityName, countryCode);
    }

}
