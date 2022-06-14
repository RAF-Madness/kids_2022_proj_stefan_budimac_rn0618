package servent.handler;

import app.AppConfig;
import servent.message.Message;

public class QuitHandler implements MessageHandler {
    private Message clientMessage;

    public QuitHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        synchronized (AppConfig.stateLock) {
            AppConfig.state.getNodes().remove(clientMessage.getSender().getNodeId());
            AppConfig.timestampedStandardPrint("Removed node with port: " + clientMessage.getSender().getPort());
        }
    }
}
