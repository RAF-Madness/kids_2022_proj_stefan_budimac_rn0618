package servent.handler;

import app.AppConfig;
import servent.message.EnterMessage;

public class EnterHandler implements MessageHandler {
    private EnterMessage clientMessage;

    public EnterHandler(EnterMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        synchronized (AppConfig.stateLock) {
            AppConfig.state.getNodes().put(clientMessage.getSender().getNodeId(), clientMessage.getSender());
        }
    }
}
