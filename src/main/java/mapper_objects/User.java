package mapper_objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String login;
    private int id;
    private String gravatar_id;
    private String company;
    @JsonProperty("public_repos")
    private int publicRepos;
}
