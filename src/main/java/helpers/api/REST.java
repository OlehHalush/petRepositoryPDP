package helpers.api;

import helpers.config2test.AppUser;
import helpers.config2test.Parameter;
import kong.unirest.HttpRequest;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class REST {

    public static HttpResponse executeAnonymousApexCode(String anonymousBody) {
        try {
            String encodedURL = getFieldFromAuthInfo("instance_url") +
                    "/services/data/v48.0/tooling/executeAnonymous?anonymousBody=" +
                    URLEncoder.encode(anonymousBody, "UTF-8");
            HttpRequest request = Unirest.get(encodedURL)
                    .header("Authorization", "Bearer " + getFieldFromAuthInfo("access_token"));
            HttpResponse response = request.asString();
            System.out.println("Response status: " + response.getStatus() + " " + response.getStatusText());
            return response;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    private static String getFieldFromAuthInfo(String field) {
        HttpResponse<String> response = Unirest
                .post(Parameter.ORG_URL.get() + "/services/oauth2/token")
                .field("grant_type", "password")
                .field("client_id", Parameter.CLIENT_ID.get())
                .field("client_secret", Parameter.CLIENT_SECRET.get())
                .field("username", AppUser.ADMIN.getUsername())
                .field("password", AppUser.ADMIN.getPassword())
                .asString();
        JSONObject body = new JSONObject(response.getBody());
        if (!body.has(field)) {
            throw new RuntimeException("Unsuccessful login\n" + body);
        }
        return body.getString(field);
    }

    public static String getInstanceUrl() {
        return getFieldFromAuthInfo("instance_url");
    }

    public static boolean isBrowserstackSessionInRunningStatus(String sessionId) {
        HttpResponse<String> response = Unirest
                .get("https://" + AppUser.getCurrentUser().getAppiumUrl().getUserInfo() + "@api-cloud.browserstack.com/app-automate/sessions/" + sessionId + ".json")
                .asString();
        JSONObject body = new JSONObject(response.getBody());
        return body.getJSONObject("automation_session").getString("status").equals("running");
    }
}