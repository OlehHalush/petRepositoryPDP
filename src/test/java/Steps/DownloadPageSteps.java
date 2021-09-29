package Steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import page.DownloadPage;

public class DownloadPageSteps extends BaseSteps {
    private DownloadPage downloadPage;

    public DownloadPageSteps(WebDriver driver) {
        super(driver);
        downloadPage = new DownloadPage(driver);
    }

    public DownloadPageSteps verifyDownloadPageIsDisplayed() {
        Assert.assertTrue("Download page is not displayed.", downloadPage.isDownloadPageDisplayed());
        return this;
    }
}
