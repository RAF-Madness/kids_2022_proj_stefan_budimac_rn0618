package servent.handler;

import app.AppConfig;
import app.model.NodeInfo;
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
        NodeInfo newNode = new NodeInfo(clientMessage.getSenderPort(), clientMessage.getSenderIpAddress(), clientMessage.getSenderId());
        Message connectionResponseMessage = new ConnectionResponseMessage(AppConfig.info.getNodeInfo(), newNode);
        synchronized (AppConfig.stateLock) {
            AppConfig.state.setPrevious(newNode);
        }
        MessageUtil.sendMessage(connectionResponseMessage);
    }
}
