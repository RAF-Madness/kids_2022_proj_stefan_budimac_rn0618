package servent.handler;

import app.AppConfig;
import app.ChaosState;
import app.model.ContactContent;
import app.model.NodeInfo;
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
        ContactContent messageContent = (ContactContent) clientMessage.getPayload();
        if (messageContent.getFirst()) {
            int newId;
            synchronized (AppConfig.idLock) {
                newId = 0;
            }
            AppConfig.info.setNodeId(newId);
            AppConfig.state.getNodes().put(AppConfig.info.getNodeId(), AppConfig.info.getNodeInfo());
            Message joinMessage = new JoinMessage(clientMessage.getReceiver(), new NodeInfo(AppConfig.BOOTSTRAP.getPort(), AppConfig.BOOTSTRAP.getIpAddress(), -1, "", ""));
            joinMessage.setPayload(newId);
            MessageUtil.sendMessage(joinMessage);
        } else {
            Message systemKnockMessage = new SystemKnockMessage(AppConfig.info.getNodeInfo(), messageContent.getFirstNodeInfo().getNodeInfo());
            AppConfig.state = new ChaosState();
            MessageUtil.sendMessage(systemKnockMessage);
        }
    }
}
