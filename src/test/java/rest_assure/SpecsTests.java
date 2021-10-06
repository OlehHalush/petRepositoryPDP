package rest_assure;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.*;

public class SpecsTests extends BaseClass {

    @BeforeTest
    public void setup() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL).build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
    }

    @AfterTest
    public void cleanup(){
        RestAssured.requestSpecification = null;
        RestAssured.responseSpecification = null;
    }

    @Test
    public void testWithUsingReqAndRespSpecsOutsideTheTest() {
        RestAssured.get();
    }

    @Test
    public void usingReqAndRespSpecsInside() {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL).build();
        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();
        RestAssuredConfig config = new RestAssuredConfig()
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL));

        RestAssured
                .given()
                .config(config)
                .spec(requestSpecification)
                .get()
                .then()
                .spec(responseSpecification);
    }
}
