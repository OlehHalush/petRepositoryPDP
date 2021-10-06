package rest_assure;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CheckResponseTime extends BaseClass {

    @Test
    public void responseTime() {
        Response response = RestAssured.get(BASE_URL);
        System.out.println(response.getTime());
        System.out.println(response.getTimeIn(TimeUnit.SECONDS));
        System.out.println(response.getTimeIn(TimeUnit.MILLISECONDS));
        System.out.println(response.getTimeIn(TimeUnit.MINUTES));
    }
}
