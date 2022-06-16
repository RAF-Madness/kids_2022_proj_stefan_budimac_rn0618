package servent.handler;

import app.AppConfig;
import servent.message.EnteredClusterMessage;

public class EnteredClusterHandler implements MessageHandler {
    private EnteredClusterMessage clientMessage;

    public EnteredClusterHandler(EnteredClusterMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        AppConfig.state.getClusterNodes().put(clientMessage.getSender().getNodeId(), clientMessage.getSender());
    }
}
