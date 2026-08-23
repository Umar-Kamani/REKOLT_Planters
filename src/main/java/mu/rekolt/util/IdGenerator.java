package mu.rekolt.util;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
 // Start the sequence at 1
    private static final AtomicInteger sequence = new AtomicInteger(1);

    public static int getNextId() {
        // Increments the value by 1 and returns it atomically
        return sequence.getAndIncrement();
    }
}

