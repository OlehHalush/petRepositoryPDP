package rest_assure;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class PostTests extends BaseClass {

    @Test
    public void postTestWithAuthorizationHeader() {
        RestAssured
                .given()
                .header("Authorization", "token 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")
                .body("{\"name\": \"deleteme4\"}")
                .when()
                .post(BASE_URL + "/user/repos")
                .then()
                .statusCode(201);
    }

    @Test
    public void postTestWithAuthMethod() {
        RestAssured
                .given()
                .auth()
                .oauth2(TOKEN)
                .body("{\"name\": \"deleteme5\"}")
                .when()
                .post(BASE_URL + "/user/repos")
                .then()
                .statusCode(201);
    }
}
