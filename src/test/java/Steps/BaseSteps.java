package Steps;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseSteps {

    private static final int TIMEOUT = 2;

    protected WebDriver driver;

    public BaseSteps(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        this.driver = driver;
    }
}
