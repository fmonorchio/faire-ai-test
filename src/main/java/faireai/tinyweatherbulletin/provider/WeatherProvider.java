package faireai.tinyweatherbulletin.provider;

import faireai.tinyweatherbulletin.domain.openweather.OpenWeatherForecastsResponse;

public interface WeatherProvider {

    Object getGeoByCityName(String cityName);

    OpenWeatherForecastsResponse getWeatherByCity(String cityName);

}
