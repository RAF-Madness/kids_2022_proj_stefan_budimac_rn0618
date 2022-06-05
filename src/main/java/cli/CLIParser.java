package cli;

import app.Cancellable;
import cli.command.CLICommand;
import servent.SimpleServentListener;

import java.util.ArrayList;
import java.util.List;

public class CLIParser implements Runnable, Cancellable {
    private volatile boolean working = true;
    private final List<CLICommand> commandList;

    public CLIParser(SimpleServentListener serventListener) {
        this.commandList = new ArrayList<>();
    }

    @Override
    public void run() {

    }

    @Override
    public void stop() {
        this.working = false;
    }
}
