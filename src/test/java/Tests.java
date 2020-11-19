import Steps.HomePageSteps;
import config.Driver;

public class Tests {

    public static void beforeClass() {
        Driver.getDriver();
    }

    public static void beforeTest() {
        Driver.openHomePage();
    }

    public static void openSignUpPage() {
        new HomePageSteps(Driver.getDriver())
                .verifyHomePageIsDisplayed()
                .clickGetStartedButton()
                .verifySignUpPageIsDisplayed();
    }

    public static void openFeaturesPage() {
        new HomePageSteps(Driver.getDriver())
                .verifyHomePageIsDisplayed()
                .clickFeaturesButton()
                .verifyFeaturesPageIsDisplayed();
    }

    public static void openDownloadPage() {
        new HomePageSteps(Driver.getDriver())
                .verifyHomePageIsDisplayed()
                .clickDownloadButton()
                .verifyDownloadPageIsDisplayed();
    }

    public static void afterClass() {
        Driver.quitDriver();
    }
}
