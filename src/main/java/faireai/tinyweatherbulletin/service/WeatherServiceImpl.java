package faireai.tinyweatherbulletin.service;

import faireai.tinyweatherbulletin.domain.openweather.OpenWeatherResponse;
import faireai.tinyweatherbulletin.provider.WeatherProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    WeatherProvider weatherProvider;

    @Override
    public OpenWeatherResponse getWeatherByCity(String cityName) {
        return weatherProvider.getWeatherByCity(cityName);
    }

}
