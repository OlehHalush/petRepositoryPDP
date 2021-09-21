package Steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page.SignUpPage;

public class SignUpPageSteps extends BaseSteps {
    private SignUpPage signUpPage;

    public SignUpPageSteps(WebDriver driver) {
        super(driver);
        signUpPage = new SignUpPage(driver);
    }

    public SignUpPageSteps verifySignUpPageIsDisplayed() {
        wait.until(driver -> signUpPage.isSignUpPageDisplayed());
        Assert.assertTrue(signUpPage.isSignUpPageDisplayed(), "Sign Up page is not displayed");
        return this;
    }
}
