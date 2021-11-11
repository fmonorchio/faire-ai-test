package faireai.core.util;

import java.time.Instant;
import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoUnit.MINUTES;

public final class InstantUtils {

    public static Instant median(Instant first, Instant second) {

        LocalDateTime dateTime1 = LocalDateTime.ofInstant(first, UTC);
        LocalDateTime dateTime2 = LocalDateTime.ofInstant(second, UTC);

        long difference = MINUTES.between(dateTime1, dateTime2);
        long interval = difference / 2;

        return first.plus(interval, MINUTES);
    }

    private InstantUtils() {
    }

}
