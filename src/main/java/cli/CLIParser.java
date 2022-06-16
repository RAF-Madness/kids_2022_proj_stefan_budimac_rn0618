package cli;

import app.AppConfig;
import app.Cancellable;
import cli.command.*;
import servent.SimpleServentListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLIParser implements Runnable, Cancellable {
    private volatile boolean working = true;
    private final List<CLICommand> commandList;

    public CLIParser(SimpleServentListener serventListener) {
        this.commandList = new ArrayList<>();

        commandList.add(new QuitCommand(this, serventListener));
        commandList.add(new PurgeCommand());
        commandList.add(new StatusCommand());
        commandList.add(new StartCommand());
        commandList.add(new ResultCommand());
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (working) {
            String commandLine = scanner.nextLine();
            int spacePosition = commandLine.indexOf(" ");
            String commandName, commandArgs = null;
            if (spacePosition != -1) {
                commandName = commandLine.substring(0, spacePosition);
                commandArgs = commandLine.substring(spacePosition + 1);
            } else {
                commandName = commandLine;
            }
            boolean found = false;
            for (CLICommand command : commandList) {
                if (command.commandName().equals(commandName)) {
                    command.execute(commandArgs);
                    found = true;
                    break;
                }
            }
            if (!found) {
                AppConfig.timestampedErrorPrint("Unknown command: " + commandName);
            }
        }
        scanner.close();
    }

    @Override
    public void stop() {
        this.working = false;
    }
}
