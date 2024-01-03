package model;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class User {
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void main(String[] args) {
        User user1 = new User("Oleg", "123");
        User user2 = new User("Oleg2", "1232");
        User user3 = new User("Oleg3", "1233");
        User user4 = new User("Oleg4", "1234");
//        List<List<User>> lists = List.of(List.of(user1, user2), List.of(user3, user4));
        List<User> lists = List.of(user1, user2);
        System.out.println();
//        lists.stream().
//        Callable
        Supplier<User> s = () -> new User("Oleh", "123123");
        s.get();
    }
}
