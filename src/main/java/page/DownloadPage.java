package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DownloadPage extends BasePage {

    @FindBy(xpath = "//h1[@class='apps__header__title' and text()='Time tracking apps']")
    private WebElement timeTrackingAppsHeader;

    public DownloadPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDownloadPageDisplayed() {
        return timeTrackingAppsHeader.isDisplayed();
    }
    //asd
}
