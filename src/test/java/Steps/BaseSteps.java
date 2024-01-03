package Steps;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseSteps {

    private static final int TIMEOUT = 5;

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseSteps(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

//    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
