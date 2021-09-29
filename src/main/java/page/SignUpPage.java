package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {

    @FindBy(xpath = "//div[@class='cl-sign-up-container']//div[text()='Sign Up']")
    private WebElement signUpHeader;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSignUpPageDisplayed() {
        return signUpHeader.isDisplayed();
    }

}
