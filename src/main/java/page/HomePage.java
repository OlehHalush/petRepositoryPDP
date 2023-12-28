package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "btn-get-started")
    private WebElement getStartedButton;

    @FindBy(id = "features")
    private WebElement featuresButton;

    @FindBy(id = "downloads")
    private WebElement downloadButton;

    public boolean isHomePageDisplayed() {
        return getStartedButton.isDisplayed();
    }

    public SignUpPage clickGetStartedButton() {
        getStartedButton.click();
        return new SignUpPage(driver);
    }

    public FeaturesPage clickFeaturesButton() {
        featuresButton.click();
        return new FeaturesPage(driver);
    }

    public DownloadPage clickDownloadButton() {
        downloadButton.click();
        return new DownloadPage(driver);
    }
}
