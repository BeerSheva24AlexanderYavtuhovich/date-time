package telran.time;

import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Arrays;

public class PastTemporalDateProximity implements TemporalAdjuster {
    private final Temporal[] temporals;

    public PastTemporalDateProximity(Temporal[] temporals) {
        this.temporals = Arrays.copyOf(temporals, temporals.length);
        Arrays.sort(this.temporals, this::compare);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        int index = findIndex(temporal);
        return (index >= 0)
                ? temporal
                        .with(ChronoField.YEAR, temporals[index].get(ChronoField.YEAR))
                        .with(ChronoField.MONTH_OF_YEAR, temporals[index].get(ChronoField.MONTH_OF_YEAR))
                        .with(ChronoField.DAY_OF_MONTH, temporals[index].get(ChronoField.DAY_OF_MONTH))
                : null;
    }

    private int compare(Temporal t1, Temporal t2) {
        return (int) t2.until(t1, ChronoUnit.DAYS);
    }

    private int findIndex(Temporal temporal) {
        int left = 0;
        int right = temporals.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = compare(temporals[mid], temporal);

            if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

}