package structural.proxy;

public class CommandExecutorImpl implements CommandExecutor{
    @Override
    public void runCommand(String cmd) {
        System.out.println("Executing command: " + cmd);
    }
}
