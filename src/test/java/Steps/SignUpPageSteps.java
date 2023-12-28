package Steps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import page.SignUpPage;

public class SignUpPageSteps extends BaseSteps {
    private SignUpPage signUpPage;

    public SignUpPageSteps(WebDriver driver) {
        super(driver);
        signUpPage = new SignUpPage(driver);
    }

    @Step
    public SignUpPageSteps verifySignUpPageIsDisplayed() {
        Assertions.assertTrue(signUpPage.isSignUpPageDisplayed(), "Sign Up page is not displayed");
        return this;
    }
}
