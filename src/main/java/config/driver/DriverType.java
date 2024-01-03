package config.driver;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import io.github.bonigarcia.wdm.managers.InternetExplorerDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum DriverType {

    FIREFOX {
        public MutableCapabilities getDesiredCapabilities() {
            return new FirefoxOptions();
        }

        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
            FirefoxOptions options = (FirefoxOptions) capabilities;
            FirefoxDriverManager.firefoxdriver().setup();
            return new FirefoxDriver(options);
        }
    }, CHROME {
        public MutableCapabilities getDesiredCapabilities() {
            ChromeOptions chromeOptions = new ChromeOptions();

            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("enableVNC", true);

            chromeOptions.setCapability("selenoid:options", selenoidOptions);
            chromeOptions.setCapability("browserName", "chrome");
            return chromeOptions;
        }

        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
            ChromeOptions options = (ChromeOptions) capabilities;
            ChromeDriverManager.chromedriver().setup();
            return new ChromeDriver(options);
        }
    }, IE {
        public MutableCapabilities getDesiredCapabilities() {
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.setCapability("ensureCleanSession", true);
            options.setCapability("ignoreProtectedModeSettings", true);
            options.setCapability("requireWindowFocus", true);
            options.setCapability("nativeEvents", false);
            options.setCapability("unexpectedAlertBehaviour", "accept");
            options.setCapability("ignoreProtectedModeSettings", true);
            options.setCapability("disable-popup-blocking", true);
            options.setCapability("PersistentHover", false);
            options.setCapability("RequireWindowFocus ", true);
            return options;
        }

        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
            InternetExplorerOptions options = (InternetExplorerOptions) capabilities;
            InternetExplorerDriverManager.iedriver().setup();
            WebDriver localDriver = new InternetExplorerDriver(options);
            localDriver.manage().window().maximize();
            return localDriver;
        }
    }, SAFARI {
        public MutableCapabilities getDesiredCapabilities() {
            SafariOptions options = new SafariOptions();
            options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            options.setCapability("javascriptEnabled", true);
            options.setCapability("profile.password_manager_enabled", false);
            options.setCapability("credentials_enable_service", false);
            options.setUseTechnologyPreview(true);
            return options;
        }

        public WebDriver getWebDriverObject(MutableCapabilities capabilities) {
            SafariOptions options = (SafariOptions) capabilities;
            WebDriver localDriver = new SafariDriver(options);
            localDriver.manage().window().maximize();
            return localDriver;
        }
    };

    public abstract MutableCapabilities getDesiredCapabilities();

    public abstract WebDriver getWebDriverObject(MutableCapabilities capabilities);

}
