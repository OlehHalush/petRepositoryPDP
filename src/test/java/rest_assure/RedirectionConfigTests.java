package rest_assure;

import io.restassured.RestAssured;
import io.restassured.config.RedirectConfig;
import io.restassured.response.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class RedirectionConfigTests extends BaseClass {

    @Test
    public void redirectionTestWithoutConfig() {
        Response resp = RestAssured.get(BASE_URL + "/repos/twitter/bootstrap");
        resp.then()
                .statusCode(200);
    }

    @Test
    public void redirectionTestWithProhibitedRedirection() {
        RestAssured.config = RestAssured.config()
                .redirect(RedirectConfig.redirectConfig().followRedirects(false));

        RestAssured.get(BASE_URL + "/repos/twitter/bootstrap")
                .then()
                .statusCode(301);

        RestAssured.config = RestAssured.config()
                .redirect(RedirectConfig.redirectConfig().followRedirects(true));
    }

    @Test
    public void redirectionTestWithMaxRedirectionsAllowed() {
        RestAssured.config = RestAssured.config()
                .redirect(RedirectConfig.redirectConfig().followRedirects(true).maxRedirects(2));

        RestAssured.get(BASE_URL + "/repos/twitter/bootstrap")
                .then()
                .statusCode(200);
    }

}
