package helpers.config2test;

import helpers.api.REST;
import helpers.utils.NetUtils;
import helpers.utils.Timeouts;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriverException;

import javax.mail.Authenticator;
import javax.mail.NoSuchProviderException;
import javax.mail.Store;
import javax.mail.URLName;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Session {

    public static long IDLE_TIMEOUT_SEC = 300; //5min
    public static long IDLE_TIMEOUT_MS = IDLE_TIMEOUT_SEC * 1000; //5 min
    public static long USAGE_TIMEOUT_MS = 5400000; //90 min
    public static final String EXCEPTION_APPIUM_SERVER_SIDE = "An unknown server-side error occurred while processing the command";
    private static final long IMPLICIT_WAIT_SEC = Timeouts.getTimeoutM().getSeconds();
    private IOSDriver<MobileElement> driver;
    private AppUser appUser;
    private Long lastTimeUsage, firstTimeUsage;
    private boolean isLocationAlertApproved;
    private String sessionId;

    public static Session start(AppUser appUser) {
        return new Session()
                .setAppUser(appUser)
                .setNewDriver();
    }

    private Session setAppUser(AppUser appUser) {
        this.appUser = appUser;
        return this;
    }

    private Session setNewDriver() {
        firstTimeUsage = System.currentTimeMillis();
        lastTimeUsage = System.currentTimeMillis();
        driver = new IOSDriver<>(appUser.getAppiumUrl(), appUser.getAppiumCapabilities());
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_SEC, TimeUnit.SECONDS);
        sessionId = driver.getSessionId().toString();
        return this;
    }

    public IOSDriver<MobileElement> getDriver() {
        lastTimeUsage = System.currentTimeMillis();
        return driver;
    }

    public void quitDriver() {
        try {
            driver.quit();
        } catch (WebDriverException ignored) {
        }
    }

    public void setLocationAlertApproved() {
        isLocationAlertApproved = true;
    }

    public boolean isLocationAlertApproved() {
        return isLocationAlertApproved;
    }

    //Check if idle time is less then 5 min
    // And session is in Running status because
    // BrowserStack terminates idle sessions over 5 min
    public boolean isExpired() throws UnknownHostException {
        return hasIdleTimePassed() && !NetUtils.isLocalHost(AppUser.getCurrentUser().getAppiumUrl()) && !sessionIsStillRunning();
    }

    //Check if session runs over 1.5 hours because
    // BrowserStack terminates sessions over 2 hours
    public boolean isSessionRunTooLong() {
        return (System.currentTimeMillis() - firstTimeUsage) > USAGE_TIMEOUT_MS;
    }


    private boolean hasIdleTimePassed() {
        return (System.currentTimeMillis() - lastTimeUsage) > IDLE_TIMEOUT_MS;
    }

    private boolean sessionIsStillRunning() {
        return REST.isBrowserstackSessionInRunningStatus(sessionId);
    }

    public static Session getInstance(Properties props) {
        return new Session(props, (Authenticator) null);
    }

    public Store getStore() throws NoSuchProviderException {
        return this.getStore(this.getProperty("mail.store.protocol"));
    }

    public Store getStore(String protocol) throws NoSuchProviderException {
        return this.getStore(new URLName(protocol, (String)null, -1, (String)null, (String)null, (String)null));
    }
}
