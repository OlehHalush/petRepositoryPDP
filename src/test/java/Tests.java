import Steps.HomePageSteps;
import config.driver.Driver;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Epic("Epic1")
@Feature("Feature1")
public class Tests {

    @BeforeMethod(description = "Open home page")
    public void beforeMethod() {
        Driver.openHomePage();
    }

    @Test(description = "Open sign up page")
    @Description("description for 'Open sign up page' test")
    @Issue("AMT-1111")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Story1")
    public void openSignUpPage() {
        new HomePageSteps(Driver.getDriver())
                .verifyHomePageIsDisplayed()
                .clickGetStartedButton()
                .verifySignUpPageIsDisplayed();
    }

    @Test(description = "Open features page")
    @Description("description for 'Open features page' test")
    @Issue("AMT-2222")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Story2")
    public void openFeaturesPage() {
        new HomePageSteps(Driver.getDriver())
                .verifyHomePageIsDisplayed()
                .clickFeaturesButton()
                .verifyFeaturesPageIsDisplayed();
    }

    @Test(description = "Open download page")
    @Description("description for 'Open download page' test")
    @Issue("AMT-3333")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Story3")
    public void openDownloadPage() {
        new HomePageSteps(Driver.getDriver())
                .verifyHomePageIsDisplayed()
                .clickDownloadButton()
                .verifyDownloadPageIsDisplayed();
    }

    @AfterClass(description = "Quit drivers")
    public void afterMethod() {
        System.out.println("QUIT DRIVER");
        Driver.quitDriver();
    }

}
