import Steps.HomePageSteps;
import config.driver.Driver;
import listeners.AttachScreenshotOnFailListener;
import org.testng.annotations.*;
import model.User;

public class Tests2 {

    @BeforeMethod
    public void beforeMethod() {
        Driver.openHomePage();
    }

    @Test
    public void openSignUpPage() {
        new HomePageSteps(Driver.getDriver())
                .verifyHomePageIsDisplayed()
                .clickGetStartedButton()
                .verifySignUpPageIsDisplayed();
    }

    @Test
    public void openDownloadPage() {
        new HomePageSteps(Driver.getDriver())
                .verifyHomePageIsDisplayed()
                .clickDownloadButton()
                .verifyDownloadPageIsDisplayed();
    }

    @Test
    public void openFeaturesPage() {
        new HomePageSteps(Driver.getDriver())
                .verifyHomePageIsDisplayed()
                .clickFeaturesButton()
                .verifyFeaturesPageIsDisplayed();
    }

    @AfterMethod
    public void afterMethod() {
        Driver.quitDriver();
    }

}
