import Steps.HomePageSteps;
import config.Driver;
import org.testng.annotations.*;
import model.User;

public class Tests3 {

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
                .verifyDownloadPageIsDisplayed(new User("User3", "Password3"));
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
