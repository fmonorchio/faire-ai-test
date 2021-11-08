package faireai.tinyweatherbulletin.service;

import faireai.tinyweatherbulletin.domain.openweather.OpenWeatherForecastsResponse;

public interface WeatherService {

    OpenWeatherForecastsResponse getWeatherByCity(String cityName);

}
