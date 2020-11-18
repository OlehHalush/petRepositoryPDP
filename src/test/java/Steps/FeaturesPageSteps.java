package Steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import page.FeaturesPage;

public class FeaturesPageSteps extends BaseSteps {
    private FeaturesPage featuresPage;

    public FeaturesPageSteps(WebDriver driver) {
        super(driver);
        featuresPage = new FeaturesPage(driver);
    }

    public FeaturesPageSteps verifyFeaturesPageIsDisplayed() {
        wait.until(driver -> featuresPage.isFeaturePageDisplayed());
        Assert.assertTrue("Features page is not displayed.", featuresPage.isFeaturePageDisplayed());
        return this;
    }
}
