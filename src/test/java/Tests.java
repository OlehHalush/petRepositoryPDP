import Steps.HomePageSteps;
import config.Driver;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class Tests {

    @Before
    public void openHomePage() {
        Driver.openHomePage();
    }

    @Test
    public void simpleTest() {
        new HomePageSteps(Driver.getDriver())
                .verifyHomePageIsDisplayed()
                .clickGetStartedButton();
    }

    @Test
    public void simpleTest2() {
        new HomePageSteps(Driver.getDriver())
                .verifyHomePageIsDisplayed()
                .clickFeaturesButton()
                .verifyFeaturesPageIsDisplayed();
    }

    @Test
    public void simpleTest3() {
        new HomePageSteps(Driver.getDriver())
                .verifyHomePageIsDisplayed()
                .clickDownloadButton()
                .verifyDownloadPageIsDisplayed();
    }

    @AfterClass
    public static void quitDriver() {
        Driver.quitDriver();
    }
}
