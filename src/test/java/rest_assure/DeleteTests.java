package rest_assure;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class DeleteTests extends BaseClass {

    @Test
    public void deleteTest() {
        RestAssured
                .given()
                .auth()
                .oauth2(TOKEN)
                .when()
                .delete(BASE_URL + "/repos/OlehHalush/deleteme")
                .then()
                .statusCode(204);
    }
}
