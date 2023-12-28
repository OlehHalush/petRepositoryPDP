package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FeaturesPage extends BasePage {
    @FindBy(xpath = "//h5[text()='TIMEKEEPING']")
    private WebElement timekeepingHeader;

    public FeaturesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTimekeepingHeaderDisplayed(){
        return timekeepingHeader.isDisplayed();
    }
}
