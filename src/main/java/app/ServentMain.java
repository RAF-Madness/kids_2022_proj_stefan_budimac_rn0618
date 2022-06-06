package app;

import cli.CLIParser;
import servent.ServentInitializer;
import servent.SimpleServentListener;

public class ServentMain {
    public static void main(String[] args) {
        if (args.length != 1) {
            AppConfig.timestampedErrorPrint("Please provide an id for this servent. Exiting...");
            System.exit(0);
        }
        int workerNumber = -1;
        int port;
        try {
            workerNumber = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            AppConfig.timestampedErrorPrint("Servent id should be an integer. Exiting...");
            System.exit(0);
        }
        AppConfig.readConfig(workerNumber);
        try {
            port = AppConfig.info.getPort();
            if (port < 1000 || port > 2000) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            AppConfig.timestampedErrorPrint("Port number is invalid, should be in range 1000-2000. Exiting...");
            System.exit(0);
        }
        AppConfig.timestampedStandardPrint("Starting servent " + AppConfig.info);

        SimpleServentListener serventListener = new SimpleServentListener();
        Thread listenerThread = new Thread(serventListener);
        listenerThread.start();

        CLIParser cliParser = new CLIParser(serventListener);
        Thread parserThread = new Thread(cliParser);
        parserThread.start();

        ServentInitializer serventInitializer = new ServentInitializer();
        Thread initializerThread = new Thread(serventInitializer);
        initializerThread.start();
    }
}
