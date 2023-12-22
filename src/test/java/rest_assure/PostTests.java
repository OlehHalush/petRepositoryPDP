package rest_assure;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import mapper_objects.Repo;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PostTests extends BaseClass {

    private Repo repo;

    @BeforeClass
    public void generateRepoDto() {
        repo = new Repo();
        repo.setName(new Faker().bothify("?????????"));

    }

    @Test
    public void postTestWithAuthorizationHeader() {
        RestAssured
                .given()
                .header("Authorization", "token " + TOKEN)
                .body(repo)
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
                .body(repo)
                .when()
                .post(BASE_URL + "/user/repos")
                .then()
                .statusCode(201);
    }

    @AfterMethod
    public void clean() {
        RestAssured
                .given()
                .auth()
                .oauth2(TOKEN)
                .when()
                .delete(BASE_URL + "/repos/OlehHalush/" + repo.getName())
                .then()
                .statusCode(204);
    }
}
