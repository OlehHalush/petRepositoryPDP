package structural.proxy;

import java.util.HashMap;
import java.util.Map;

//https://www.digitalocean.com/community/tutorials/proxy-design-pattern
public class RunTest {
    public static void main(String[] args) {
        CommandExecutor executor = new CommandExecutorProxy("Oleh", "123123");
        try {
            executor.runCommand("ls");
            executor.runCommand("rm");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "s");
        map.put(2, "s");
        map.put(3, "s");
    }

}
