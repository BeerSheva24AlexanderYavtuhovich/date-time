package telran.time;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Arrays;

public class PastTemporalDateProximity implements TemporalAdjuster {
    private final LocalDate[] localDates;
    private final Temporal[] temporals;

    public PastTemporalDateProximity(Temporal[] temporals) {
        this.temporals = temporals;
        Arrays.sort(this.temporals);
        this.localDates = new LocalDate[temporals.length];
        getLocalDatesFromTemporals(temporals);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        LocalDate searchDate = LocalDate.from(temporal);
        int index = findIndex(searchDate);
        return (index >= 0) ? temporal
                .with(ChronoField.YEAR, temporals[index].get(ChronoField.YEAR))
                .with(ChronoField.MONTH_OF_YEAR, temporals[index].get(ChronoField.MONTH_OF_YEAR))
                .with(ChronoField.DAY_OF_MONTH, temporals[index].get(ChronoField.DAY_OF_MONTH))
                : null;
    }

    private void getLocalDatesFromTemporals(Temporal[] temporals) {
        for (int i = 0; i < temporals.length; i++) {
            this.localDates[i] = LocalDate.from(temporals[i]);
        }
    }

    private int findIndex(LocalDate searchDate) {
        int left = 0;
        int right = localDates.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (localDates[mid].isBefore(searchDate)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

}