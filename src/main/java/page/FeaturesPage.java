package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FeaturesPage extends BasePage {
    @FindBy(xpath = "//div[@class='features_page']//h2[text()='Time tracker']")
    private WebElement timeTrackerHeader;

    public FeaturesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isFeaturePageDisplayed() {
        return timeTrackerHeader.isDisplayed();
    }
}
