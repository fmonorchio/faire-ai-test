package faireai.tinyweatherbulletin.provider;

import faireai.tinyweatherbulletin.domain.openweather.OpenWeatherForecastsResponse;
import faireai.tinyweatherbulletin.domain.openweather.OpenWeatherGeoCityResponse;

public interface WeatherProvider {

    OpenWeatherGeoCityResponse getGeoByCityName(String cityName);

    OpenWeatherForecastsResponse getWeatherByCity(String cityName);

}
