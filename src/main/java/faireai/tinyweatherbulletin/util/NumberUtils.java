package faireai.tinyweatherbulletin.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

@UtilityClass
public class NumberUtils {

    public double round(double number, int decimals) {

        return BigDecimal.valueOf(number)
                .setScale(decimals, HALF_UP)
                .doubleValue();
    }

}
