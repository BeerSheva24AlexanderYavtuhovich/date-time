package telran.time;

import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class PastTemporalDateProximity implements TemporalAdjuster {
    // TODO
    // some incapsulation
    // array of temporals supporting day, month, year (Dates)
    // no need to check it

    @Override
    public Temporal adjustInto(Temporal temporal) {
        // TODO
        // return the temporal for the encapsulated array that is a nearest in past
        throw new UnsupportedOperationException("Unimplemented method 'adjustInto'");
    }

}
