package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//header//img[@alt='Clockify logo']")
    private WebElement logo;

    protected void clickOnLogo() {
        logo.click();
    }

    protected boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

}
