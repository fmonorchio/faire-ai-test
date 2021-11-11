package faireai.tinyweatherbulletin.other;

import com.google.common.collect.Lists;
import faireai.core.domain.Forecasts;
import faireai.core.domain.Measures;
import faireai.core.enumeration.HourType;
import faireai.tinyweatherbulletin.config.ApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

        int hourlyInterval = config.getHourlyInterval();
        Map<HourType, List<Measures>> groupedByHourType = Lists.partition(forecasts, hourlyInterval).stream()
                .map(lm -> lm.stream().reduce(Measures::merge))
                .filter(Optional::isPresent)
                .map(Optional::get)
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
