package faireai.tinyweatherbulletin.service;

import faireai.core.domain.Forecasts;
import faireai.core.domain.GeoCoordinates;
import faireai.core.domain.Measures;
import faireai.core.domain.Weather;
import faireai.core.service.WeatherService;
import faireai.tinyweatherbulletin.other.MeasureAggregator;
import faireai.core.provider.WeatherProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherProvider weatherProvider;

    @Autowired
    private MeasureAggregator measureAggregator;

    @Override
    @Cacheable(value = "weather", key = "#cityName.#countryCode")
    public Weather getWeatherByCity(String cityName, String countryCode) {

        GeoCoordinates geo = weatherProvider.getGeoByCityName(cityName, countryCode);

        List<Measures> forecasts = weatherProvider.getForecastsByGeo(
                geo.getLatitude(),
                geo.getLongitude()
        );

        Forecasts aggregated = measureAggregator.aggregate(forecasts);
        return new Weather(
                geo.getLatitude(),
                geo.getLongitude(),
                aggregated
        );
    }

}
