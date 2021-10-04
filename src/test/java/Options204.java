import org.apache.http.client.methods.HttpOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class Options204 extends BaseClass {

    @Test
    public void optionsReturnsCorrectMethodsList() throws IOException {
        String header = "Access-Control-Allow-Methods";
        String expectedValue = "GET, POST, PATCH, PUT, DELETE";

        HttpOptions request = new HttpOptions(BASE_URL);
        response = client.execute(request);
        String actualHeaderValue = getHeaderViaStream(response, header);
        assertEquals(actualHeaderValue, expectedValue);
    }
}
