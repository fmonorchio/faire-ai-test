package faireai.tinyweatherbulletin.service;

import faireai.tinyweatherbulletin.domain.openweather.OpenWeatherResponse;

public interface WeatherService {

    OpenWeatherResponse getWeatherByCity(String cityName);

}
