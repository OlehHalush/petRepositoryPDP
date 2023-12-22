package rest_assure;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class SpecsTests extends BaseClass {

    @BeforeMethod
    public void setup() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL + "/rate_limit").build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        enableLogging();
    }

    @AfterMethod
    public void cleanup() {
        RestAssured.requestSpecification = null;
        RestAssured.responseSpecification = null;
    }

    @Test
    public void testWithUsingReqAndRespSpecsFromBeforeMethod() {
        //RequestSpecification from Before method
        RestAssured.get();
    }

    @Test
    public void usingReqAndRespSpecsFromBaseClass() throws JsonProcessingException {
        //RequestSpecification from Before class
        RestAssured
                .given()
                .spec(baseRequestSpec)
                .basePath("/rate_limit")
                .get()
                .then()
                .spec(baseResponseSpec)
                .rootPath("resources.core")
                .body("limit", equalTo(60))
                .body("remaining", equalTo(60))
                .body("resource", equalTo("core"))
                .rootPath("resources.search")
                .body("limit", equalTo(10))
                .body("resource", equalTo("search"));
    }
}
