package faireai.tinyweatherbulletin.service;

import faireai.tinyweatherbulletin.domain.HourType;
import faireai.tinyweatherbulletin.domain.WeatherAggregation;
import faireai.tinyweatherbulletin.domain.WeatherForecasts;
import faireai.tinyweatherbulletin.domain.WeatherResponse;
import faireai.tinyweatherbulletin.domain.openweather.forecasts.HourlyForecast;
import faireai.tinyweatherbulletin.domain.openweather.forecasts.OpenWeatherForecastsResponse;
import faireai.tinyweatherbulletin.domain.openweather.geo.GeoCityData;
import faireai.tinyweatherbulletin.provider.WeatherProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

import static faireai.tinyweatherbulletin.domain.openweather.forecasts.HourlyForecast.hourlyInterval;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherProvider weatherProvider;

    @Autowired
    private HourTypeSelector hourTypeSelector;

    @Override
    public WeatherResponse getWeatherByCity(String cityName) {

        GeoCityData city = weatherProvider.getGeoByCityName(cityName)
                .getCityOrThrow();

        OpenWeatherForecastsResponse response = weatherProvider.getForecastsByGeo(
                city.getLatitude(),
                city.getLongitude()
        );

        Map<Instant, WeatherAggregation> aggregated = response.getHourly().stream()
                .collect(
                        groupingBy(
                                hourlyInterval(2),
                                reducing(
                                        new WeatherAggregation(),
                                        HourlyForecast::toWeatherAggregation,
                                        WeatherAggregation::aggregate
                                )));

        Map<HourType, Map<Instant, WeatherAggregation>> collect = aggregated.entrySet().stream()
                .collect(groupingBy(
                        e -> hourTypeSelector.getHourType(e.getKey()),
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue)
                ));

        return new WeatherResponse(
                response.getLatitude(),
                response.getLongitude(),
                new WeatherForecasts(
                        collect.get(HourType.WORK),
                        collect.get(HourType.VACATION)
                )
        );
    }

}
