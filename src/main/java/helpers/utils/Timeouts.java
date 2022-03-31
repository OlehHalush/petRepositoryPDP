package helpers.utils;

import java.time.Duration;

public class Timeouts {
    private final static int XS = 200;
    private final static int S = 1000;
    private final static int M = 2000;
    private final static int L = 5000;
    private final static int XL = 20000;
    private final static int XXL = 60000;
    private final static int XXXL = 300000;
    private static Byte timeoutsMultiplier;

    private static byte getTimeoutsMultiplier() {
        if (timeoutsMultiplier == null) {
            String timeoutsMultiplierRunParameter = System.getProperty("run.timeoutsMultiplier");
            timeoutsMultiplier = timeoutsMultiplierRunParameter != null ? Byte.parseByte(timeoutsMultiplierRunParameter) : 1;
        }
        return timeoutsMultiplier;
    }

    public static Duration getTimeoutXS() {
        return Duration.ofMillis(getTimeoutsMultiplier() * XS);
    }

    public static Duration getTimeoutS() {
        return Duration.ofMillis(getTimeoutsMultiplier() * S);
    }

    public static Duration getTimeoutM() {
        return Duration.ofMillis(getTimeoutsMultiplier() * M);
    }

    public static Duration getTimeoutL() {
        return Duration.ofMillis(getTimeoutsMultiplier() * L);
    }

    public static Duration getTimeoutXL() {
        return Duration.ofMillis(getTimeoutsMultiplier() * XL);
    }

    public static Duration getTimeoutXXL() {
        return Duration.ofMillis(getTimeoutsMultiplier() * XXL);
    }

    public static Duration getTimeoutXXXL() {return Duration.ofMillis(getTimeoutsMultiplier() * XXXL);}

}