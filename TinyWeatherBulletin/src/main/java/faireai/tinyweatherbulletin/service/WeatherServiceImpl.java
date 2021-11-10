package faireai.tinyweatherbulletin.service;

import faireai.core.domain.GeoCoordinates;
import faireai.core.enumeration.HourType;
import faireai.core.domain.Measures;
import faireai.core.domain.Forecasts;
import faireai.core.domain.Weather;
import faireai.core.service.WeatherService;
import faireai.tinyweatherbulletin.other.HourTypeSelector;
import faireai.core.provider.WeatherProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherProvider weatherProvider;

    @Autowired
    private HourTypeSelector hourTypeSelector;

    @Override
    public Weather getWeatherByCity(String cityName, String countryCode) {

        GeoCoordinates geo = weatherProvider.getGeoByCityName(cityName);

        List<Measures> forecasts = weatherProvider.getForecastsByGeo(
                geo.getLatitude(),
                geo.getLongitude()
        );

        Map<Instant, Measures> aggregate = forecasts.stream()
                .collect(
                        groupingBy(
                                Measures.hourlyInterval(2),
                                reducing(
                                        new Measures(),
                                        Measures::merge
                                )
                        )
                );

        Map<HourType, Map<Instant, Measures>> collect = aggregate.entrySet().stream()
                .collect(groupingBy(
                        e -> hourTypeSelector.getHourType(e.getKey()),
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue)
                ));

        return new Weather(
                geo.getLatitude(),
                geo.getLongitude(),
                new Forecasts(
                        collect.get(HourType.WORK),
                        collect.get(HourType.VACATION)
                )
        );
    }

}
