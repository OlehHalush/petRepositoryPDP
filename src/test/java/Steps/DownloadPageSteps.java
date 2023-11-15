package Steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page.DownloadPage;
import model.User;

public class DownloadPageSteps extends BaseSteps {
    private DownloadPage downloadPage;

    public DownloadPageSteps(WebDriver driver) {
        super(driver);
        downloadPage = new DownloadPage(driver);
    }

    @Step("Verify download page is displayed")
    public DownloadPageSteps verifyDownloadPageIsDisplayed() {
        wait.until(driver -> downloadPage.isDownloadPageDisplayed());
        Assert.assertTrue(downloadPage.isDownloadPageDisplayed(), "Download page is not displayed.");
        saveScreenshot();
        return this;
    }
}
