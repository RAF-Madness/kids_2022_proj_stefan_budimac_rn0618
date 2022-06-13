package cli.command;

import cli.CLIParser;
import servent.SimpleServentListener;

public class QuitCommand implements CLICommand {
    private CLIParser parser;
    private SimpleServentListener listener;

    public QuitCommand(CLIParser parser, SimpleServentListener listener) {
        this.parser = parser;
        this.listener = listener;
    }

    @Override
    public String commandName() {
        return "quit";
    }

    @Override
    public void execute(String args) {

    }
}
