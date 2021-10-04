import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class PostAndDelete extends BaseClass {

    @Test
    public void createResponseReturns201() throws IOException {
        HttpPost request = new HttpPost(BASE_URL + "/user/repos");
        request.setHeader(HttpHeaders.AUTHORIZATION, "token <your_token>");
        String json = "{\"name\": \"deleteme\"}";
        request.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        response = client.execute(request);
        int actualStatusCode = response.getStatusLine().getStatusCode();
        assertEquals(actualStatusCode, 201);
    }

    @Test
    public void successDeleteReturns204() throws IOException {
        HttpDelete request = new HttpDelete(BASE_URL + "/repos/OlehHalush/deleteme");
        request.setHeader(HttpHeaders.AUTHORIZATION, "token <your_token>");
        response = client.execute(request);
        int actualStatusCode = response.getStatusLine().getStatusCode();
        assertEquals(actualStatusCode, 204);
    }
}
