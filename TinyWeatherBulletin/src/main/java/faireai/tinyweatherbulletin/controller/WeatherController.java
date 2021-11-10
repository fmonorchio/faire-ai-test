package faireai.tinyweatherbulletin.controller;

import faireai.core.domain.Weather;
import faireai.core.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Slf4j
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public Weather getWeatherByCity(
            @NotEmpty @Size(min = 3) @RequestParam String cityName,
            @RequestParam(required = false) String countryCode) {

        log.info("Received request for weather forecasts (city: {}, country: {})", cityName, countryCode);
        return weatherService.getWeatherByCity(cityName, countryCode);
    }

}
