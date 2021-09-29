package Steps;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import page.SignUpPage;

public class SignUpPageSteps extends BaseSteps {
    private SignUpPage signUpPage;

    public SignUpPageSteps(WebDriver driver) {
        super(driver);
        signUpPage = new SignUpPage(driver);
    }

    public SignUpPageSteps verifySignUpPageIsDisplayed() {
        Assert.assertTrue("Sign Up page is not displayed", signUpPage.isSignUpPageDisplayed());
        return this;
    }
}
