package servent.handler;

import app.AppConfig;
import app.ChaosState;
import app.model.NodeInfo;
import servent.message.ConnectionRequestMessage;
import servent.message.Message;
import servent.message.util.MessageUtil;

public class WelcomeHandler implements MessageHandler {
    private Message clientMessage;

    public WelcomeHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        NodeInfo content = (NodeInfo) clientMessage.getMessageContent().get(0);
        AppConfig.info.setWorkerId(content.getId());
        AppConfig.state = (ChaosState) clientMessage.getMessageContent().get(1);
        NodeInfo senderInfo = new NodeInfo(clientMessage.getSenderPort(), clientMessage.getSenderIpAddress(), clientMessage.getSenderId());
        synchronized (AppConfig.stateLock) {
            AppConfig.state.setPrevious(senderInfo);
        }
        Message connectionRequestMessage = new ConnectionRequestMessage(content, AppConfig.state.getFirstNode());
        MessageUtil.sendMessage(connectionRequestMessage);
    }
}
