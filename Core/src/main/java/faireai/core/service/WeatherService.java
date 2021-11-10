package faireai.core.service;

import faireai.core.domain.Weather;

public interface WeatherService {

    Weather getWeatherByCity(String cityName, String countryCode);

}
