package helpers.config2test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class User {

    private String username, password;
    private LocalDateTime blockTime;
    private boolean inUse = false;

    public static User asUser(String username, String password) {
        return new User()
                .setUsername(username)
                .setPassword(password);
    }

    private User setUsername(String username) {
        this.username = username;
        return this;
    }

    private User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void startUsing() {
        inUse = true;
    }

    public User stopUsing() {
        inUse = false;
        return this;
    }

    public boolean inUse() {
        return inUse;
    }

    public void setApiBlocked() {
        blockTime = LocalDateTime.now();
    }

    public boolean isApiBlocked() {
        return blockTime != null && (LocalDateTime.now().truncatedTo(ChronoUnit.HOURS).isEqual(blockTime.truncatedTo(ChronoUnit.HOURS)));
    }
}
