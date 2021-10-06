package rest_assure;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class PatchTests extends BaseClass {

    @Test
    public void updateRepoName() {
        RestAssured
                .given()
                .auth()
                .oauth2(TOKEN)
                .body("{\"name\": \"deleteme\"}")
                .when()
                .patch(BASE_URL + "/repos/OlehHalush/deletemePatch")
                .then()
                .statusCode(200);
    }
}
