package faireai.tinyweatherbulletin.provider;

import faireai.tinyweatherbulletin.domain.openweather.OpenWeatherResponse;

public interface WeatherProvider {

    OpenWeatherResponse getWeatherByCity(String cityName);

}
