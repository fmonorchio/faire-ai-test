package faireai.tinyweatherbulletin.controller;

import faireai.tinyweatherbulletin.domain.openweather.OpenWeatherResponse;
import faireai.tinyweatherbulletin.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public OpenWeatherResponse getWeatherByCity(@RequestParam String cityName) {
        log.info("Received request for weather forecasts (city: {})", cityName);
        return weatherService.getWeatherByCity(cityName);
    }

}
