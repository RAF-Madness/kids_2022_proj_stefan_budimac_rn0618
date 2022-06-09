package servent.handler;

import app.AppConfig;
import app.model.NodeInfo;
import servent.message.Message;
import servent.message.SystemKnockMessage;
import servent.message.util.MessageUtil;

public class ContactHandler implements MessageHandler {
    private Message clientMessage;

    public ContactHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        Message systemKnockMessage = new SystemKnockMessage(AppConfig.info.getNodeInfo(), (NodeInfo) clientMessage.getMessageContent());
        MessageUtil.sendMessage(systemKnockMessage);
    }
}
