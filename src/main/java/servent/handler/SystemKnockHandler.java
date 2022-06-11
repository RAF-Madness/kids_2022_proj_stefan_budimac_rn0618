package servent.handler;

import app.AppConfig;
import app.model.KnockAnswer;
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
        int newId;
        synchronized (AppConfig.idLock) {
            newId = AppConfig.WORKER_ID++;
        }
        Message welcomeMessage = new WelcomeMessage(-2, -1);
        NodeInfo newNode = (NodeInfo) clientMessage.getMessageContent();
        synchronized (AppConfig.stateLock) {
            AppConfig.state.setNext(newNode);
        }
        KnockAnswer knockAnswer = new KnockAnswer(newId, AppConfig.state, AppConfig.info.getNodeInfo());
        welcomeMessage.setMessageContent(knockAnswer);
        MessageUtil.sendMessage(welcomeMessage, newNode);
    }
}
