import com.microsoft.playwright.*;
import org.testng.annotations.*;

import java.nio.charset.StandardCharsets;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FirstTest {

    // Shared between all tests in this class.
    Playwright playwright;
    Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page mainPage;

    @BeforeClass
    void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterClass
    void closeBrowser() {
        playwright.close();
    }

    @BeforeMethod
    void createContextAndPage() {
        context = browser.newContext();
        mainPage = context.newPage();
    }

    @AfterMethod
    void closeContext() {
        context.close();
    }

    @Test
    public void test1() {
        mainPage.navigate("https://www.saucedemo.com/");
        mainPage.locator("[data-test=\"username\"]").fill("standard_user");
        mainPage.locator("[data-test=\"password\"]").fill("secret_sauce");
        mainPage.locator("[data-test=\"login-button\"]").click();
        assertThat(mainPage.locator("#header_container")).containsText("Swag Labs");
    }

    @Test
    public void test2() {
        mainPage.navigate("https://www.saucedemo.com/");
        mainPage.locator("[data-test=\"username\"]").fill("standard_user");
        mainPage.locator("[data-test=\"password\"]").fill("secret_sauce");
        mainPage.locator("[data-test=\"login-button\"]").click();
        assertThat(mainPage.locator("#header_container")).containsText("Swag Labs");
    }

    @Test
    public void test3() {
        mainPage.navigate("https://www.saucedemo.com/");
        mainPage.locator("[data-test=\"username\"]").fill("standard_user");
        mainPage.locator("[data-test=\"password\"]").fill("secret_sauce");
        mainPage.locator("[data-test=\"login-button\"]").click();
        assertThat(mainPage.locator("#header_container")).containsText("Swag Labs");
    }
}
