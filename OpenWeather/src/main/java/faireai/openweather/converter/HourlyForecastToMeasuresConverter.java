package faireai.openweather.converter;

import faireai.core.domain.DataPoints;
import faireai.core.domain.Measures;
import faireai.openweather.domain.HourlyForecast;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HourlyForecastToMeasuresConverter implements Converter<HourlyForecast, Measures> {

    @Override
    public Measures convert(HourlyForecast source) {

        return new Measures(
                source.getDate(),
                new DataPoints(source.getTemperature()),
                new DataPoints(source.getHumidity())
        );
    }

}
