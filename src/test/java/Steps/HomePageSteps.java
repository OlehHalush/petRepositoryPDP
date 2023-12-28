package Steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import page.HomePage;

public class HomePageSteps extends BaseSteps {
    private HomePage homePage;

    public HomePageSteps(WebDriver driver) {
        super(driver);
        homePage = new HomePage(driver);
    }

    @Step
    public HomePageSteps verifyHomePageIsDisplayed() {
        Assertions.assertTrue(homePage.isHomePageDisplayed(), "Home page is not displayed.");
        return this;
    }

    @Step
    public SignUpPageSteps clickGetStartedButton() {
        homePage.clickGetStartedButton();
        return new SignUpPageSteps(driver);
    }

    @Step
    public FeaturesPageSteps clickFeaturesButton() {
        homePage.clickFeaturesButton();
        return new FeaturesPageSteps(driver);
    }

    @Step
    public DownloadPageSteps clickDownloadButton() {
        homePage.clickDownloadButton();
        return new DownloadPageSteps(driver);
    }
}
