package Steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page.HomePage;

public class HomePageSteps extends BaseSteps {
    private HomePage homePage;

    public HomePageSteps(WebDriver driver) {
        super(driver);
        homePage = new HomePage(driver);
    }

    @Step("Verify home page is displayed")
    public HomePageSteps verifyHomePageIsDisplayed() {
        wait.until(driver -> homePage.isHomePageDisplayed());
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page is not displayed.");
        saveScreenshot();
        return this;
    }

    @Step("Click get started button")
    public SignUpPageSteps clickGetStartedButton() {
        homePage.clickGetStartedButton();
        saveScreenshot();
        return new SignUpPageSteps(driver);
    }

    @Step("Click features button")
    public FeaturesPageSteps clickFeaturesButton() {
        homePage.clickFeaturesButton();
        saveScreenshot();
        return new FeaturesPageSteps(driver);
    }

    @Step("Click download button")
    public DownloadPageSteps clickDownloadButton() {
        homePage.clickDownloadButton();
        saveScreenshot();
        return new DownloadPageSteps(driver);
    }
}
