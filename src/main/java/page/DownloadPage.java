package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DownloadPage extends BasePage {

    @FindBy(xpath = "//h5[text()='DESKTOP']")
    private WebElement desktopHeader;

    public DownloadPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDesktopHeaderDisplayed() {
        return desktopHeader.isDisplayed();
    }
}
