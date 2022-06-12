package servent.handler;

import app.AppConfig;
import app.model.WelcomeContent;
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
            newId = AppConfig.info.getWorkerId() + 1;
        }
        Message welcomeMessage = new WelcomeMessage(clientMessage.getReceiverInfo(), clientMessage.getSenderInfo());
        synchronized (AppConfig.stateLock) {
            AppConfig.state.setNext(clientMessage.getSenderInfo());
        }
        WelcomeContent welcomeContent = new WelcomeContent(newId, AppConfig.state);
        welcomeMessage.setMessageContent(welcomeContent);
        MessageUtil.sendMessage(welcomeMessage);
    }
}
