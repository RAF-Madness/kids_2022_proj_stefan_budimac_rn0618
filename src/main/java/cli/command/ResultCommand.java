package cli.command;


public class ResultCommand implements CLICommand {

    @Override
    public String commandName() {
        return "result";
    }

    @Override
    public void execute(String args) {
        String[] splitArgs = args.split(" ");
        if (splitArgs.length == 1) {
            String jobName = splitArgs[0];
        } else {
            String jobName = splitArgs[0];
            String fractalId = splitArgs[1];
        }
    }
}
