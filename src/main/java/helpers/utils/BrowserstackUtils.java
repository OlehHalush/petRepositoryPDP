package utils;

import kong.unirest.GetRequest;
import kong.unirest.HttpRequest;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;

public class BrowserstackUtils {

    public static String getPublicLink(String sessionId) {
        final String USERNAME = "oleksandreskin_i1vNtK";
        final String AUTOMATE_KEY = "4T1u2kQFXcye1BZbzKz6";

        HttpRequest<GetRequest> request = Unirest
                .get("https://api.browserstack.com/automate/sessions/" + sessionId + ".json")
                .basicAuth(USERNAME, AUTOMATE_KEY);
        HttpResponse<String> response = request.asString();
        try {
            JSONObject jsonObject = new JSONObject(response.getBody());
            return jsonObject.getJSONObject("automation_session").getString("public_url");
        } catch (JSONException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(getPublicLink("50f5fc82a99043d960e893f59b8ce1f5873be2a3"));
    }
//public_url
}
