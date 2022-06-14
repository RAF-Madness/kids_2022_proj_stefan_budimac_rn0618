package servent.handler;

import app.AppConfig;
import servent.message.Message;

public class EnterHandler implements MessageHandler {
    private Message clientMessage;

    public EnterHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        synchronized (AppConfig.stateLock) {
            AppConfig.state.getNodes().put(clientMessage.getSender().getNodeId(), clientMessage.getSender());
        }
    }
}
