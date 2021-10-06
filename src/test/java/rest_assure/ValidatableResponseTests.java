package rest_assure;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class ValidatableResponseTests extends BaseClass {

    @Test
    public void basicValidatableExample() {
        RestAssured.get(BASE_URL)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("server", "GitHub.com");
    }
}
