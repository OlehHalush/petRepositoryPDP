package Steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page.DownloadPage;

public class DownloadPageSteps extends BaseSteps {
    private DownloadPage downloadPage;

    public DownloadPageSteps(WebDriver driver) {
        super(driver);
        downloadPage = new DownloadPage(driver);
    }

    public DownloadPageSteps verifyDownloadPageIsDisplayed() {
        wait.until(driver -> downloadPage.isDownloadPageDisplayed());
        Assert.assertTrue(downloadPage.isDownloadPageDisplayed(), "Download page is not displayed.");
        return this;
    }
}
