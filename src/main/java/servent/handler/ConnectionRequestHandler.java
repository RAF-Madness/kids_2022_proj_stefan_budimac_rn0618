package servent.handler;

import app.AppConfig;
import servent.message.ConnectionResponseMessage;
import servent.message.Message;
import servent.message.util.MessageUtil;

public class ConnectionRequestHandler implements MessageHandler {
    private Message clientMessage;

    public ConnectionRequestHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        Message connectionResponseMessage = new ConnectionResponseMessage(AppConfig.info.getNodeInfo(), clientMessage.getSender());
        synchronized (AppConfig.stateLock) {
            AppConfig.state.setPrevious(clientMessage.getSender());
        }
        connectionResponseMessage.setPayload(clientMessage.getPayload());
        MessageUtil.sendMessage(connectionResponseMessage);
    }
}
