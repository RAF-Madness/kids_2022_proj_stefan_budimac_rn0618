package servent.handler;

import app.AppConfig;
import app.model.NodeInfo;
import servent.message.Message;
import servent.message.WelcomeMessage;
import servent.message.util.MessageUtil;

public class SystemKnockHandler implements MessageHandler {
    private Message clientMessage;

    public SystemKnockHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        Message welcomeMessage = new WelcomeMessage(AppConfig.info.getNodeInfo(), new NodeInfo(clientMessage.getSenderPort(), clientMessage.getSenderIpAddress(), -1));
        int newId;
        synchronized (AppConfig.idLock) {
            newId = AppConfig.WORKER_ID++;
        }
        welcomeMessage.setMessageContent(new NodeInfo(clientMessage.getSenderPort(), clientMessage.getSenderIpAddress(), newId));
        MessageUtil.sendMessage(welcomeMessage);
    }
}
