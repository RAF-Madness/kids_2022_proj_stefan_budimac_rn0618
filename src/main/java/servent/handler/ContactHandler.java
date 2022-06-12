package servent.handler;

import app.AppConfig;
import app.ChaosState;
import app.model.ContactContent;
import app.model.NodeInfo;
import app.model.NodeInfoId;
import servent.message.JoinMessage;
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
        ContactContent messageContent = (ContactContent) clientMessage.getMessageContent();
        if (messageContent.getFirst()) {
            int newId;
            synchronized (AppConfig.idLock) {
                newId = 0;
                System.out.println("EVO GA ID ALOOOOOO " + newId);
            }
            AppConfig.info.setWorkerId(newId);
            AppConfig.state = new ChaosState();
            AppConfig.state.getNodes().put(AppConfig.info.getWorkerId(), AppConfig.info.getNodeInfo());
            Message joinMessage = new JoinMessage(clientMessage.getReceiverInfo(), new NodeInfo(AppConfig.BOOTSTRAP.getPort(), AppConfig.BOOTSTRAP.getIpAddress()));
            joinMessage.setMessageContent(newId);
            MessageUtil.sendMessage(joinMessage);
        } else {
            Message systemKnockMessage = new SystemKnockMessage(AppConfig.info.getNodeInfo(), messageContent.getFirstNodeInfo().getNodeInfo());
            AppConfig.state = new ChaosState();
            MessageUtil.sendMessage(systemKnockMessage);
        }
    }
}
