package rest_assure;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CheckHeaders extends BaseClass {

    @Test
    public void assertHeaders() {
        Response response = RestAssured.get(BASE_URL);
        assertEquals(response.getHeader("server"), "GitHub.com");
        assertEquals(response.getHeader("x-ratelimit-limit"), "60");
    }

    @Test
    public void checkHeaders() {
        Response response = RestAssured.get(BASE_URL);
        Headers headers = response.getHeaders();
        String server = headers.getValue("server");
        assertEquals(server, "GitHub.com");
        int size = headers.size();
        assertTrue(size > 0);
        boolean isPresent = headers.hasHeaderWithName("server");
        assertTrue(isPresent);
    }
}
