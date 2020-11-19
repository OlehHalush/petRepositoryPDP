package Steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page.HomePage;

public class HomePageSteps extends BaseSteps {
    private HomePage homePage;

    public HomePageSteps(WebDriver driver) {
        super(driver);
        homePage = new HomePage(driver);
    }

    public HomePageSteps verifyHomePageIsDisplayed() {
        wait.until(driver -> homePage.isHomePageDisplayed());
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page is not displayed.");
        return this;
    }

    public SignUpPageSteps clickGetStartedButton() {
        homePage.clickGetStartedButton();
        return new SignUpPageSteps(driver);
    }

    public FeaturesPageSteps clickFeaturesButton() {
        homePage.clickFeaturesButton();
        return new FeaturesPageSteps(driver);
    }

    public DownloadPageSteps clickDownloadButton() {
        homePage.clickDownloadButton();
        return new DownloadPageSteps(driver);
    }
}
