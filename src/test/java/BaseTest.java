import Steps.BaseSteps;
import config.Driver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    BaseSteps baseSteps = new BaseSteps(Driver.getDriver());

    @BeforeEach
    public void openHomePage() {
        baseSteps.openHomePage();
    }

    @AfterAll
    public static void quitDriver() {
        Driver.quitDriver();
    }
}
