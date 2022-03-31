package helpers.config2test;

import helpers.utils.ThreadUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriverException;

import java.net.UnknownHostException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Driver {

    private static final ThreadLocal<HashMap<AppUser, Session>> mobileDrivers = new ThreadLocal<>();

    public static HashMap<AppUser, Session> getDrivers() {
        if (mobileDrivers.get() == null) {
            mobileDrivers.set(ThreadUtils.getMobileDrivers());
        }
        return mobileDrivers.get();
    }

    public static IOSDriver<MobileElement> getDriver() {
        return Driver.getDriver(AppUser.getCurrentUser());
    }

    private static IOSDriver<MobileElement> getDriver(AppUser appUser) {
        if (Driver.getDrivers().get(appUser) == null) {
            Driver.getDrivers().put(appUser, Session.start(appUser));
        }
        return Driver.getDrivers().get(appUser).getDriver();
    }

    public static void quitDriver() {
        Driver.quitDriver(AppUser.getCurrentUser());
    }

    public static void quitDriver(AppUser appUser) {
        if (Driver.getDrivers().get(appUser) != null) {
            Driver.getDrivers().get(appUser).quitDriver();
            Driver.getDrivers().put(appUser, null);
        }
    }

    public static boolean isSessionActive(AppUser appUser) throws UnknownHostException {
        return Driver.getDrivers().get(appUser) != null && !Driver.getDrivers().get(appUser).isExpired();
    }

    public static void switchCurrentDriverContextToNativeApp() {
        Driver.getDriver().context("NATIVE_APP");
    }

    public static void switchCurrentDriverContextToWebApp() {
        List<String> handles = Driver.getWebContexts();
        if (handles.size() == 0) {
            throw new IllegalStateException("No web context available");
        }
        Driver.getDriver().context(handles.get(handles.size() - 1)).getTitle();
    }

    public static void hideKeyboard() {
        try {
            Driver.getDriver().hideKeyboard();
        } catch (WebDriverException ignored) {
        }
    }

    public static void acceptAlertToUseLocationIfAppears() {
        try {
            String alertText = Driver.getDriver().switchTo().alert().getText();
            if (StringUtils.normalizeSpace(alertText).matches("Allow.*Amalia.*[use|access].*location.*")) {
                Driver.getDriver().setSetting("acceptAlertButtonSelector", "**/XCUIElementTypeButton[`label == 'Allow While Using App'`]");
                Driver.getDriver().switchTo().alert().accept();
                Driver.getSession().setLocationAlertApproved();
            }
        } catch (WebDriverException ignored) {
        }
    }

    public static void acceptAlertToUseLocationIfAppearsOverSession() {
        if (Driver.getSession().isLocationAlertApproved()) {
            return;
        }
        Driver.acceptAlertToUseLocationIfAppears();
    }

    public static Session getSession() {
        if (Driver.getDrivers().get(AppUser.getCurrentUser()) != null) {
            return Driver.getDrivers().get(AppUser.getCurrentUser());
        }
        throw new IllegalStateException("No session for current user " + AppUser.getCurrentUser().name());
    }

    public static boolean isAlertDisplayed() {
        try {
            Driver.getDriver().switchTo().alert();
            return true;
        } catch (WebDriverException ignored) {
        }
        return false;
    }

    public static ZoneId getDeviceTimeZoneId() {
        String deviceTime = (String) Driver.getDriver().executeScript("mobile: getDeviceTime");
        return ZonedDateTime.parse(deviceTime).getZone();
    }

    public static List<String> getWebContexts() {
        return Driver.getDriver().getContextHandles().stream()
                .filter(f -> !f.equals("NATIVE_APP"))
                .collect(Collectors.toList());
    }

    public static boolean switchedToWebContextByTitle(String title) {
        try {
            List<String> webContexts = Driver.getWebContexts();
            Collections.reverse(webContexts);
            return webContexts.stream().anyMatch(c -> Driver.getDriver().context(c).getTitle().contains(title));
        } catch (WebDriverException ignored) {
            return false;
        }
    }
}
