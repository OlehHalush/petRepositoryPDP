package config.driver;

import config.ConfigReader;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {
    public static final boolean IS_REMOTE = ConfigReader.getRunParameter("remote").equalsIgnoreCase("true");
    public static final DriverType DRIVER_TYPE = DriverType.valueOf(ConfigReader.getRunParameter("driver.type").toUpperCase());
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private static WebDriver createNewDriver() {
        MutableCapabilities capabilities = DRIVER_TYPE.getDesiredCapabilities();
        if (IS_REMOTE) {
            System.out.println("RUNNING REMOTE");
            driverThreadLocal.set(createNewRemoteDriver(capabilities));
        } else {
            System.out.println("RUNNING LOCAL");
            driverThreadLocal.set(DRIVER_TYPE.getWebDriverObject(capabilities));
        }
        return driverThreadLocal.get();
    }

    private static RemoteWebDriver createNewRemoteDriver(MutableCapabilities capabilities) {
        RemoteWebDriver driver;
        try {
            ChromeOptions options = new ChromeOptions();
            driver = new RemoteWebDriver(new URL("http://node-docker:4444/wd/hub"), options); //remote run from jenkins with selenium grid
//            driver = new RemoteWebDriver(new URL("http://localhost:4444"), options); //local run with selenium grid
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