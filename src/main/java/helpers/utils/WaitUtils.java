package helpers.utils;

import helpers.config2test.Session;
import org.openqa.selenium.WebDriverException;

import java.time.Duration;
import java.util.function.Supplier;

public class WaitUtils {
    private static final Duration DEFAULT_POLL_INTERVAL = Timeouts.getTimeoutXS();
    private static final Duration DEFAULT_TIMEOUT = Timeouts.getTimeoutL();

    private WaitUtils() {
    }

    public static boolean tryWaitUntil(Supplier<Boolean> condition) {
        return tryWaitUntil(condition, DEFAULT_TIMEOUT);
    }

    public static boolean tryWaitUntil(Supplier<Boolean> condition, Duration timeout) {
        return tryWaitUntil(condition, timeout, DEFAULT_POLL_INTERVAL);
    }

    public static boolean tryWaitUntil(Supplier<Boolean> condition, Duration timeout, Duration pollInterval) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < timeout.toMillis()) {
            try {
                if (condition.get()) {
                    return true;
                }
            } catch (WebDriverException e) {
                if (e.getMessage().contains(Session.EXCEPTION_APPIUM_SERVER_SIDE)) {
                    throw e;
                }
            } catch (Exception ignored) {
            }
            waitABit(pollInterval);
        }
        return false;
    }

    public static void waitUntil(Supplier<Boolean> condition) {
        waitUntil(condition, DEFAULT_TIMEOUT);
    }

    public static void waitUntil(Supplier<Boolean> condition, Duration timeout) {
        waitUntil(condition, timeout, DEFAULT_POLL_INTERVAL);
    }

    public static void waitUntil(Supplier<Boolean> condition, Duration timeout, Duration pollInterval) {
        long startTime = System.currentTimeMillis();
        String exceptionMessage = "";
        while (System.currentTimeMillis() - startTime < timeout.toMillis()) {
            try {
                if (condition.get()) {
                    return;
                }
            } catch (WebDriverException e) {
                if (e.getMessage().contains(Session.EXCEPTION_APPIUM_SERVER_SIDE)) {
                    throw e;
                }
            } catch (Exception e) {
                exceptionMessage = e.getMessage();
            }
            waitABit(pollInterval);
        }
        throw new IllegalStateException("Timeout exceeded.\n" + exceptionMessage);
    }

    public static void waitABit() {
        waitABit(Timeouts.getTimeoutS());
    }

    public static void waitABit(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException ignored) {
        }
    }
}
