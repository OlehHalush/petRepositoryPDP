package helpers.utils;

import helpers.config2test.AppUser;
import helpers.config2test.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreadUtils {

    private static final List<Thread> threads = new ArrayList<>();
    private static final Map<Thread, HashMap<AppUser, Session>> mobileDrivers = new HashMap<>();

    public synchronized static void registerCurrentThread() {
        if (!threads.contains(Thread.currentThread()) && !threads.contains(null)) {
            threads.add(Thread.currentThread());
        }
        if (!threads.contains(Thread.currentThread()) && threads.contains(null)) {
            threads.set(threads.indexOf(null), Thread.currentThread());
        }
    }

    public synchronized static HashMap<AppUser, Session> getMobileDrivers() {
        if (!mobileDrivers.containsKey(Thread.currentThread())) {
            mobileDrivers.put(Thread.currentThread(), new HashMap<>());
        }
        return mobileDrivers.get(Thread.currentThread());
    }

    public synchronized static void quitDrivers() {
        for (Thread thread : mobileDrivers.keySet()) {
            for (AppUser appUser : mobileDrivers.get(thread).keySet()) {
                if (mobileDrivers.get(thread).get(appUser) != null) {
                    mobileDrivers.get(thread).get(appUser).quitDriver();
                    mobileDrivers.get(thread).put(appUser, null);
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
                for (AppUser appUser : mobileDrivers.get(thread).keySet()) {
                    if (mobileDrivers.get(thread).get(appUser) != null) {
                        mobileDrivers.get(thread).get(appUser).quitDriver();
                        mobileDrivers.get(thread).put(appUser, null);
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