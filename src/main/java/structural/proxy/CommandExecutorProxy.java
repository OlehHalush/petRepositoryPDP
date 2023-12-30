package structural.proxy;

public class CommandExecutorProxy implements CommandExecutor {
    private boolean isAdmin;
    private CommandExecutor executor;

    public CommandExecutorProxy(String userName, String userPassword) {
        if (userName.equals("Oleh") && userPassword.equals("qwerty"))
            isAdmin = true;
        executor = new CommandExecutorImpl();
    }

    @Override
    public void runCommand(String cmd) {
        if (isAdmin) {
            executor.runCommand(cmd);
        } else {
            if (cmd.startsWith("rm")) {
                throw new IllegalArgumentException("rm command is not allowed for non admin users");
            } else {
                executor.runCommand(cmd);
            }
        }
    }
}
