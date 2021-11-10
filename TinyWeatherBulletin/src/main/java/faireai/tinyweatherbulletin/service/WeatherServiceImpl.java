package faireai.tinyweatherbulletin.service;

import faireai.core.domain.GeoCoordinates;
import faireai.core.domain.Measures;
import faireai.core.domain.Weather;
import faireai.core.provider.WeatherProvider;
import faireai.core.service.WeatherService;
import faireai.tinyweatherbulletin.other.MeasureAggregator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherProvider weatherProvider;

    @Autowired
    private MeasureAggregator aggregator;

    @Override
    @Cacheable(value = "weather", key = "#cityName + #countryCode")
    public Weather getWeatherByCity(String cityName, String countryCode) {

        GeoCoordinates geo = weatherProvider.getGeoByCityName(cityName, countryCode);
        double latitude = geo.getLatitude();
        double longitude = geo.getLongitude();

        log.info("{} is located at (lat: {}, lon: {})", cityName, latitude, longitude);

        List<Measures> forecasts = weatherProvider.getForecastsByGeo(latitude, longitude);

        return new Weather(
                cityName,
                countryCode,
                latitude,
                longitude,
                aggregator.aggregate(forecasts)
        );
    }

}
