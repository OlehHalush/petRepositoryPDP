package Steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import page.FeaturesPage;

public class FeaturesPageSteps extends BaseSteps {
    private FeaturesPage featuresPage;

    public FeaturesPageSteps(WebDriver driver) {
        super(driver);
        featuresPage = new FeaturesPage(driver);
    }

    @Step
    public FeaturesPageSteps verifyFeaturesPageIsDisplayed() {
        Assertions.assertTrue(featuresPage.isTimekeepingHeaderDisplayed(), "Features page is not displayed.");
        return this;
    }
}
