package faireai.tinyweatherbulletin.service;

import faireai.tinyweatherbulletin.domain.WeatherResponse;

public interface WeatherService {

    WeatherResponse getWeatherByCity(String cityName);

}
