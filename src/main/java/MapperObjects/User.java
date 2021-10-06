package MapperObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String login;
    private int id;
    private String gravatar_id;
    private String company;
    @JsonProperty("public_repos")
    private int publicRepos;

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public String getCompany() {
        return company;
    }

    public int getPublicRepos() {
        return publicRepos;
    }
}
