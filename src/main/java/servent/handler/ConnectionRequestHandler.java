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
        Message connectionResponseMessage = new ConnectionResponseMessage(AppConfig.info.getNodeInfo(), clientMessage.getSenderInfo());
        synchronized (AppConfig.stateLock) {
            AppConfig.state.setPrevious(clientMessage.getSenderInfo());
        }
        connectionResponseMessage.setMessageContent(clientMessage.getMessageContent());
        MessageUtil.sendMessage(connectionResponseMessage);
    }
}
