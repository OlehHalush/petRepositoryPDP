import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.User;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class BaseClass {
    protected static final String BASE_URL = "https://api.github.com";

    protected CloseableHttpClient client;
    protected CloseableHttpResponse response;

    @BeforeMethod
    public void setup() {
        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void tearDown() throws IOException {
        client.close();
        response.close();
    }

    protected static String getHeader(CloseableHttpResponse response, String headerName) {
        Header[] headers = response.getAllHeaders();
        List<Header> httpHeaders = Arrays.asList(headers);
        String returnHeader = "";
        for (Header header : httpHeaders) {
            if (headerName.equalsIgnoreCase(header.getName())) {
                returnHeader = header.getValue();
            }
        }

        if (returnHeader.isEmpty()) {
            throw new RuntimeException("Didn't find the header ." + headerName + "'.");
        }

        return returnHeader;
    }

    protected static String getHeaderViaStream(CloseableHttpResponse response, String headerName) {
        List<Header> httpHeaders = Arrays.asList(response.getAllHeaders());
        Header matchedHeader = httpHeaders.stream()
                .filter(header -> headerName.equalsIgnoreCase(header.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Didn't find the header ." + headerName + "'."));
        return matchedHeader.getValue();
    }

    protected static boolean isHeaderPresent(CloseableHttpResponse response, String headerName) {
        List<Header> headers = Arrays.asList(response.getAllHeaders());
        return headers.stream()
                .anyMatch(header -> header.getName().equalsIgnoreCase(headerName));
    }

    protected static Object getValueFor(JSONObject jsonObject, String key) {
        return jsonObject.get(key);
    }

    protected static User unmarshall(CloseableHttpResponse response, Class<User> userClass) throws IOException {
        String jsonBody = EntityUtils.toString(response.getEntity());
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonBody, userClass);
    }

    protected <T> T unmarshallGeneric(CloseableHttpResponse response, Class<T> userClass) throws IOException {
        String jsonBody = EntityUtils.toString(response.getEntity());
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonBody, userClass);
    }

}
