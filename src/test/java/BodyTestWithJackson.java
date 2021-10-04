import entities.NotFound;
import entities.RateLimit;
import entities.User;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class BodyTestWithJackson extends BaseClass {

    @Test
    public void returnsCorrectLogin() throws IOException {
        HttpGet get = new HttpGet(BASE_URL + "/users/andrejss88");
        response = client.execute(get);
        User user = unmarshall(response, User.class);
        assertEquals(user.getLogin(), "andrejss88");
    }

    @Test
    public void returnsCorrectId() throws IOException {
        HttpGet get = new HttpGet(BASE_URL + "/users/andrejss88");
        response = client.execute(get);
        User user = unmarshall(response, User.class);
        assertEquals(user.getId(), 11834443);
    }

    @Test
    public void notFoundMessageIsCorrect() throws IOException {
        HttpGet get = new HttpGet(BASE_URL + "/nonexistingendpoint");
        response = client.execute(get);
        NotFound notFound = unmarshallGeneric(response, NotFound.class);
        assertEquals(notFound.getMessage(), "Not Found");
    }

    @Test
    public void correctRateLimitsAreSet() throws IOException {
        HttpGet get = new HttpGet(BASE_URL + "/rate_limit");
        response = client.execute(get);
        RateLimit rateLimit = unmarshallGeneric(response, RateLimit.class);
        assertEquals(rateLimit.getCoreLimit(), 60);
        assertEquals(rateLimit.getSearchLimit(), "10");
    }

}
