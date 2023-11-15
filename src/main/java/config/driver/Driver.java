package config.driver;

import config.ConfigReader;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {
    public static final boolean IS_REMOTE = ConfigReader.getRunParameter("remote").equalsIgnoreCase("true");
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private static WebDriver createNewDriver() {
        DriverType driverType = DriverType.CHROME;
        MutableCapabilities capabilities = driverType.getDesiredCapabilities();
        if (IS_REMOTE) {
            driverThreadLocal.set(createNewRemoteDriver(capabilities));
        } else {
            driverThreadLocal.set(driverType.getWebDriverObject(capabilities));
        }
        return driverThreadLocal.get();
    }

    private static RemoteWebDriver createNewRemoteDriver(MutableCapabilities capabilities) {
        RemoteWebDriver driver;
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("\nCouldn't start REMOTE WEB DRIVER!\n", e);
        }
        driver.setFileDetector(new LocalFileDetector());
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() != null) {
            return driverThreadLocal.get();
        }
        return createNewDriver();
    }

    public static void quitDriver() {
        getDriver().quit();
        driverThreadLocal.set(null);
    }

    public static void openHomePage() {
        getDriver().get("https://clockify.me/");
    }
}