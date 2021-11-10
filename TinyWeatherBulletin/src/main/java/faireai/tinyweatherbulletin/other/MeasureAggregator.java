package faireai.tinyweatherbulletin.other;

import faireai.core.domain.Forecasts;
import faireai.core.domain.Measures;
import faireai.core.enumeration.HourType;
import faireai.tinyweatherbulletin.config.ApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static faireai.core.domain.Measures.hourlyInterval;
import static faireai.core.enumeration.HourType.VACATION;
import static faireai.core.enumeration.HourType.WORK;
import static java.util.Collections.sort;
import static java.util.stream.Collectors.*;

@Component
public class MeasureAggregator {

    @Autowired
    ApplicationConfiguration config;

    @Autowired
    private HourTypeSelector hourTypeSelector;

    public Forecasts aggregate(List<Measures> forecasts) {

        Collection<Optional<Measures>> groupedByHourlyInterval = forecasts.stream()
                .collect(
                        groupingBy(
                                hourlyInterval(config.getHourlyInterval()),
                                reducing(Measures::merge)
                        )
                ).values();

        Map<HourType, List<Measures>> groupedByHourType = groupedByHourlyInterval.stream()
                .filter(Optional::isPresent)
                .flatMap(Optional::stream)
                .collect(
                        groupingBy(
                                m -> hourTypeSelector.getHourType(m.getDate()),
                                collectingAndThen(
                                        toList(),
                                        lm -> {
                                            sort(lm);
                                            return lm;
                                        }
                                )
                        )
                );

        return new Forecasts(
                groupedByHourType.get(WORK),
                groupedByHourType.get(VACATION)
        );
    }

}
