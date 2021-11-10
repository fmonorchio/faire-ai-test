package faireai.tinyweatherbulletin.other;

import faireai.core.domain.Forecasts;
import faireai.core.domain.Measures;
import faireai.core.enumeration.HourType;
import faireai.tinyweatherbulletin.config.ApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

@Component
public class MeasureAggregator {

    @Autowired
    ApplicationConfiguration config;

    @Autowired
    private HourTypeSelector hourTypeSelector;

    public Forecasts aggregate(List<Measures> forecasts) {

        Map<Instant, Measures> aggregate = forecasts.stream()
                .collect(
                        Collectors.collectingAndThen(
                                groupingBy(
                                        Measures.hourlyInterval(config.getHourlyInterval()),
                                        reducing(
                                                new Measures(),
                                                Measures::merge
                                        )
                                ),
                                TreeMap::new
                        )
                );

        Map<HourType, Map<Instant, Measures>> collect = aggregate.entrySet().stream()
                .collect(
                        groupingBy(
                        e -> hourTypeSelector.getHourType(e.getKey()),
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue)
                ));

        return new Forecasts(
                collect.get(HourType.WORK),
                collect.get(HourType.VACATION)
        );
    }

}
