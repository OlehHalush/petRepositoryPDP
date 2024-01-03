package listeners;

import config.driver.Driver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AttachScreenshotOnFailListener implements ITestListener {
    private static final Logger log = LoggerFactory.getLogger(AttachScreenshotOnFailListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getMethod().getConstructorOrMethod().getMethod().isAnnotationPresent(org.testng.annotations.Test.class)) {
            System.out.println("BEFORE MAKING SCREENSHOT");
            log.info("INFO LOG BEFORE SCREENSHOT");
            saveScreenshot();
            log.info("INFO LOG AFTER SCREENSHOT");
            System.out.println("AFTER MAKING SCREENSHOT");
        }
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot() {
        return ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
