package rest_assure;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class UsingGlobalVariable extends BaseClass {

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://api.github.com";
        RestAssured.basePath = "/rate_limit";
        RestAssured.rootPath = "resources.core";
    }

    @Test
    public void usingGlobalVariableTest() {
        RestAssured.get()
                .then()
                //rooPath here is taken from the variable in @BeforeTest
                .body("limit", equalTo(60))
                .body("remaining", equalTo(60))
                .body("resource", equalTo("core"))
                .rootPath("resources.search")
                .body("limit", equalTo(10))
                .body("resource", equalTo("search"));
    }
}
