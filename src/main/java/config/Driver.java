package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {

    protected static WebDriver driver = null;

    @BeforeClass
    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;

    }

    public static void quitDriver() {
        driver.quit();
    }

    public static void openHomePage() {
        getDriver().get("https://clockify.me/");
    }

}