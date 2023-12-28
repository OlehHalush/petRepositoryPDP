package Steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import page.DownloadPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DownloadPageSteps extends BaseSteps {
    private DownloadPage downloadPage;

    public DownloadPageSteps(WebDriver driver) {
        super(driver);
        downloadPage = new DownloadPage(driver);
    }

    @Step
    public DownloadPageSteps verifyDownloadPageIsDisplayed() {
        assertTrue(downloadPage.isDesktopHeaderDisplayed(), "Download page is not displayed.");
        return this;
    }
}
