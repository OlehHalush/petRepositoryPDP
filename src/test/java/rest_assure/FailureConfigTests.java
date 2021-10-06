package rest_assure;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.FailureConfig;
import io.restassured.listener.ResponseValidationFailureListener;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class FailureConfigTests extends BaseClass {

    @Test
    public void failure() {
        ResponseValidationFailureListener failureListener = (requestSpecification, responseSpecification, response) ->
                System.out.printf("We have a failure, response status was %s and body contained: %s", response.getStatusCode(), response.body().asPrettyString());

        RestAssured.config = RestAssured.config()
                        .failureConfig(FailureConfig.failureConfig().failureListeners(failureListener));

        RestAssured.get(BASE_URL + "/users/OlehHalush")
                .then()
                .body("some.path", Matchers.equalTo("something"));

    }
}
