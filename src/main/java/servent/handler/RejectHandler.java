package servent.handler;

import app.AppConfig;

public class RejectHandler implements MessageHandler {

    @Override
    public void run() {
        AppConfig.timestampedErrorPrint("Bootstrap has rejected you. Now die...");
        System.exit(0);
    }
}
