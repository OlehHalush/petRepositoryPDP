import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ResponseHeaders extends BaseClass {

    @Test
    public void contentTypeIsJson() throws IOException {
        HttpGet get = new HttpGet(BASE_URL);
        response = client.execute(get);
        ContentType contentType = ContentType.getOrDefault(response.getEntity());
        assertEquals(contentType.getMimeType(), "application/json");
    }

    @Test
    public void serverIsGitHub() throws IOException {
        HttpGet get = new HttpGet(BASE_URL);
        response = client.execute(get);
        String headerValue = getHeader(response, "Server");
        assertEquals(headerValue, "GitHub.com");
    }

    @Test
    public void xRateLimitIsSixty() throws IOException {
        HttpGet get = new HttpGet(BASE_URL);
        response = client.execute(get);
        String headerValue = getHeaderViaStream(response, "X-RateLimit-Limit");
        assertEquals(headerValue, "60");
    }

    @Test
    public void eTagIsPresent() throws IOException {
        HttpGet get = new HttpGet(BASE_URL);
        response = client.execute(get);
        boolean isETagPresentInResponse = isHeaderPresent(response, "ETag");
        assertTrue(isETagPresentInResponse);
    }

}
