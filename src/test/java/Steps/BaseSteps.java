package Steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseSteps {
    private static final int TIMEOUT = 2;

    protected WebDriver driver;

    public BaseSteps(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
        this.driver = driver;
    }

    @Step
    public void openHomePage() {
        driver.get("https://clockify.me/");
    }
}
