package servent.handler;

import app.AppConfig;
import app.model.WelcomeContent;
import servent.message.SystemKnockMessage;
import servent.message.WelcomeMessage;
import servent.message.util.MessageUtil;

public class SystemKnockHandler implements MessageHandler {
    private SystemKnockMessage clientMessage;

    public SystemKnockHandler(SystemKnockMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        int newId;
        synchronized (AppConfig.idLock) {
            newId = AppConfig.info.getNodeId() + 1;
        }
        WelcomeMessage welcomeMessage = new WelcomeMessage(clientMessage.getReceiver(), clientMessage.getSender());
        synchronized (AppConfig.stateLock) {
            AppConfig.info.setNext(clientMessage.getSender());
        }
        WelcomeContent welcomeContent = new WelcomeContent(newId, AppConfig.state);
        welcomeMessage.setPayload(welcomeContent);
        MessageUtil.sendMessage(welcomeMessage);
    }
}
