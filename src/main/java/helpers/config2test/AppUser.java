package com.customertimes.config;

import com.customertimes.util.NetUtils;
import com.customertimes.util.Timeouts;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum AppUser {

    SALES_REP("Sales Rep") {
        @Override
        public String getUsernames() {
            return Parameter.SALES_REP_USERNAME.get();
        }

        @Override
        public String getPasswords() {
            return Parameter.SALES_REP_PASSWORD.get();
        }

        @Override
        public String getDeviceName() {
            return Parameter.SALES_REP_DEVICE_NAME.get();
        }

        @Override
        public String getPlatformVersion() {
            return Parameter.SALES_REP_PLATFORM_VERSION.get();
        }

        @Override
        public String getAppiumUrlString() {
            return Parameter.SALES_REP_APPIUM_URL.get();
        }
    },
    SALES_MANAGER("Sales Manager") {
        @Override
        public String getUsernames() {
            return Parameter.SALES_MANAGER_USERNAME.get();
        }

        @Override
        public String getPasswords() {
            return Parameter.SALES_MANAGER_PASSWORD.get();
        }

        @Override
        public String getDeviceName() {
            return Parameter.SALES_MANAGER_DEVICE_NAME.get();
        }

        @Override
        public String getPlatformVersion() {
            return Parameter.SALES_MANAGER_PLATFORM_VERSION.get();
        }

        @Override
        public String getAppiumUrlString() {
            return Parameter.SALES_MANAGER_APPIUM_URL.get();
        }
    },

    SALES_REP_SECONDARY("Sales Rep Secondary") {
        @Override
        public String getUsernames() {
            return Parameter.SALES_REP_SECONDARY_USERNAME.get();
        }

        @Override
        public String getPasswords() {
            return Parameter.SALES_REP_SECONDARY_PASSWORD.get();
        }

        @Override
        public String getDeviceName() {
            return Parameter.SALES_REP_SECONDARY_DEVICE_NAME.get();
        }

        @Override
        public String getPlatformVersion() {
            return Parameter.SALES_REP_SECONDARY_PLATFORM_VERSION.get();
        }

        @Override
        public String getAppiumUrlString() {
            return Parameter.SALES_REP_SECONDARY_APPIUM_URL.get();
        }
    },

    ADMIN("Admin") {
        @Override
        public String getUsernames() {
            return Parameter.ADMIN_USERNAME.get();
        }

        @Override
        public String getPasswords() {
            return Parameter.ADMIN_PASSWORD.get();
        }

        @Override
        public String getDeviceName() {
            throw new IllegalStateException("Admin does not use mobile device");
        }

        @Override
        public String getPlatformVersion() {
            throw new IllegalStateException("Admin does not use mobile device");
        }

        @Override
        public MutableCapabilities getAppiumCapabilities() {
            throw new IllegalStateException("Admin does not use mobile device");
        }

        @Override
        public String getAppiumUrlString() {
            throw new IllegalStateException("Admin does not use mobile device");
        }
    },
    DEFAULT("Default") {
        @Override
        public String getUsernames() {
            return fromString(Parameter.DEFAULT_USER.get()).getUsernames();
        }

        @Override
        public String getPasswords() {
            return fromString(Parameter.DEFAULT_USER.get()).getPasswords();
        }

        @Override
        public String getDeviceName() {
            return fromString(Parameter.DEFAULT_USER.get()).getDeviceName();
        }

        @Override
        public String getPlatformVersion() {
            return fromString(Parameter.DEFAULT_USER.get()).getPlatformVersion();
        }

        @Override
        public MutableCapabilities getAppiumCapabilities() {
            return fromString(Parameter.DEFAULT_USER.get()).getAppiumCapabilities();
        }

        @Override
        public String getAppiumUrlString() {
            return fromString(Parameter.DEFAULT_USER.get()).getAppiumUrlString();
        }
    };

    private static final ThreadLocal<AppUser> currentAppUser = ThreadLocal.withInitial(() -> AppUser.DEFAULT);
    private final ThreadLocal<List<User>> users = new ThreadLocal<>();
    private final String role;

    AppUser(String role) {
        this.role = role;
    }

    private User getUser() {
        readUsersFromConfig();
        return users.get().stream().filter(User::inUse).findFirst().orElseGet(this::getNextFreeUser);
    }

    private User getNextFreeUser() {
        User user = users.get().stream().filter(u -> !u.inUse() && !u.isApiBlocked()).findFirst()
                .orElseThrow(() -> new IllegalStateException("No available users"));
        user.startUsing();
        return user;
    }

    private void readUsersFromConfig() {
        if (users.get() != null && users.get().size() > 0) {
            return;
        }
        String[] usernameValues = getUsernames().split(";");
        String[] passwordValues = getPasswords().split(";");
        users.set(IntStream.range(0, usernameValues.length).boxed()
                .map(i -> User.asUser(usernameValues[i], passwordValues[i]))
                .collect(Collectors.toList()));
    }

    public static AppUser getCurrentUser() {
        return currentAppUser.get();
    }

    public static void switchToUser(AppUser appUser) {
        currentAppUser.set(appUser.equals(DEFAULT)
                ? fromString(Parameter.DEFAULT_USER.get())
                : appUser);
    }

    public void setApiBlockedAndStopUsing() {
        getUser().stopUsing().setApiBlocked();
    }

    public String getUsername() {
        return getUser().getUsername();
    }

    public String getPassword() {
        return getUser().getPassword();
    }

    public MutableCapabilities getAppiumCapabilities() {
        MutableCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APP, Parameter.APP.get());
        capabilities.setCapability("iosInstallPause", 2000);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, "false");
        capabilities.setCapability(IOSMobileCapabilityType.USE_NEW_WDA, "false");
        capabilities.setCapability(IOSMobileCapabilityType.USE_PREBUILT_WDA, "true");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, Duration.ofSeconds(Session.IDLE_TIMEOUT_SEC).toMillis());
        capabilities.setCapability("deviceOrientation", "landscape");
        capabilities.setCapability("project", "Amalia Automation");
        capabilities.setCapability("build", "Amalia " + Parameter.RUN_MARKET.get());
        capabilities.setCapability("name", "Cucumber Test " + new Date());
        capabilities.setCapability("includeSafariInWebviews", "true");
        capabilities.setCapability("webviewConnectTimeout", Timeouts.getTimeoutS().toMillis());
        capabilities.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, NetUtils.nextFreeWdaPort());
        capabilities.setCapability("browserstack.idleTimeout", Session.IDLE_TIMEOUT_SEC);
        capabilities.setCapability("realMobile", "true");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getDeviceName());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, getPlatformVersion());
        capabilities.setCapability("forceAppLaunch", "true");
        capabilities.setCapability("screenshotQuality", "2");
        return capabilities;
    }

    public URL getAppiumUrl() {
        String appiumUrlString = getAppiumUrlString();
        try {
            return new URL(appiumUrlString);
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Cannot parse appium url from: " + appiumUrlString + "");
        }
    }

    public abstract String getUsernames();

    public abstract String getPasswords();

    public abstract String getDeviceName();

    public abstract String getPlatformVersion();

    public abstract String getAppiumUrlString();

    public static AppUser fromString(String name) {
        for (AppUser appUser : AppUser.values()) {
            if (appUser.role.equalsIgnoreCase(name)) {
                return appUser;
            }
        }
        throw new IllegalArgumentException("No constant with text " + name + " found");
    }
}
