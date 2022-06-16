package servent.handler;

import app.AppConfig;
import servent.message.ConnectionRequestMessage;
import servent.message.ConnectionResponseMessage;
import servent.message.util.MessageUtil;

public class ConnectionRequestHandler implements MessageHandler {
    private ConnectionRequestMessage clientMessage;

    public ConnectionRequestHandler(ConnectionRequestMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        ConnectionResponseMessage connectionResponseMessage = new ConnectionResponseMessage(AppConfig.info.getNodeInfo(), clientMessage.getSender());
        synchronized (AppConfig.stateLock) {
            AppConfig.info.setPrevious(clientMessage.getSender());
        }
        connectionResponseMessage.setPayload(clientMessage.getPayload());
        MessageUtil.sendMessage(connectionResponseMessage);
    }
}
