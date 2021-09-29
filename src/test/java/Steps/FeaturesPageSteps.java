package Steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page.FeaturesPage;

public class FeaturesPageSteps extends BaseSteps {
    private FeaturesPage featuresPage;

    public FeaturesPageSteps(WebDriver driver) {
        super(driver);
        featuresPage = new FeaturesPage(driver);
    }

    @Step("Verify features page is displayed")
    public FeaturesPageSteps verifyFeaturesPageIsDisplayed() {
        wait.until(driver -> featuresPage.isFeaturePageDisplayed());
        Assert.assertTrue(featuresPage.isFeaturePageDisplayed(), "Features page is not displayed.");
        saveScreenshot();
        return this;
    }
}
