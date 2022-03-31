package utils;

import com.customertimes.config.AppUser;
import com.customertimes.config.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreadUtils {

    private static final List<Thread> threads = new ArrayList<>();
    private static final Map<Thread, HashMap<AppUser, Session>> drivers = new HashMap<>();

    public synchronized static void registerCurrentThread() {
        if (!threads.contains(Thread.currentThread()) && !threads.contains(null)) {
            threads.add(Thread.currentThread());
        }
        if (!threads.contains(Thread.currentThread()) && threads.contains(null)) {
            threads.set(threads.indexOf(null), Thread.currentThread());
        }
    }

    public synchronized static HashMap<AppUser, Session> getDrivers() {
        if (!drivers.containsKey(Thread.currentThread())) {
            drivers.put(Thread.currentThread(), new HashMap<>());
        }
        return drivers.get(Thread.currentThread());
    }

    public synchronized static void quitDrivers() {
        for (Thread thread : drivers.keySet()) {
            for (AppUser appUser : drivers.get(thread).keySet()) {
                if (drivers.get(thread).get(appUser) != null) {
                    drivers.get(thread).get(appUser).quitWebDriver();
                    drivers.get(thread).get(appUser).quitMobileDriver();
                    drivers.get(thread).put(appUser, null);
                }
            }
            if (threads.contains(thread)) {
                threads.set(threads.indexOf(thread), null);
            }
        }
    }

    public synchronized static void quitIdleThreadsDrivers() {
        for (Thread thread : threads) {
            if (thread != null && thread.getState().equals(Thread.State.WAITING)) {
                for (AppUser appUser : drivers.get(thread).keySet()) {
                    if (drivers.get(thread).get(appUser) != null) {
                        drivers.get(thread).get(appUser).quitWebDriver();
                        drivers.get(thread).get(appUser).quitMobileDriver();
                        drivers.get(thread).put(appUser, null);
                    }
                }
                threads.set(threads.indexOf(thread), null);
            }
        }
    }

    public synchronized static String getRunParameter(String runParameter) {
        int threadNum = threads.indexOf(Thread.currentThread()) + 1;
        if (threadNum > 1) {
            return "thread" + threadNum + "." + runParameter;
        }
        return runParameter;
    }
}