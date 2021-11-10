package faireai.core.util;

import java.math.BigDecimal;
import java.util.stream.DoubleStream;

import static java.math.RoundingMode.HALF_UP;

public final class NumberUtils {

    public static double avg(double... numbers) {

        return DoubleStream.of(numbers).average()
                .orElseThrow();
    }

    public static double round(double number, int decimals) {

        return BigDecimal.valueOf(number)
                .setScale(decimals, HALF_UP)
                .doubleValue();
    }

    private NumberUtils() {
    }

}
