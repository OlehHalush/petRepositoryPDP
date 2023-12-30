package creational.singleton;
//https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples
public class DbConnection {
    private static DbConnection instance;

    private DbConnection() {
    }

    public static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }
}
