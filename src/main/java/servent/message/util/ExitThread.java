package servent.message.util;

import app.AppConfig;

public class ExitThread implements Runnable {
    @Override
    public void run() {
        AppConfig.timestampedStandardPrint("Exiting the system...");
        System.exit(0);
    }
}
