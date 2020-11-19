package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {

//    protected static WebDriver driver = null;
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();


    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            WebDriverManager.chromedriver().setup();
            driverThreadLocal.set(new ChromeDriver());
//            driverThreadLocal.get().manage().window().maximize();
        }
        return driverThreadLocal.get();

    }

    public static void quitDriver() {
        getDriver().quit();
        driverThreadLocal.set(null);
    }

    public static void openHomePage() {
        getDriver().get("https://clockify.me/");
    }

}