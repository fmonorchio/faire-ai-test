package faireai.core.util;

import java.time.Instant;
import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoUnit.MINUTES;

//TODO: Missing tests
public final class InstantUtils {

    public static Instant median(Instant first, Instant second) {

        LocalDateTime dateTime1 = LocalDateTime.ofInstant(first, UTC);
        LocalDateTime dateTime2 = LocalDateTime.ofInstant(second, UTC);

        long difference = MINUTES.between(dateTime1, dateTime2);

        return first.plus(difference, MINUTES);
    }

    private InstantUtils() {
    }

}