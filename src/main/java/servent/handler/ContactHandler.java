package servent.handler;

import app.AppConfig;
import app.model.FirstNodeInfo;
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
        FirstNodeInfo messageContent = (FirstNodeInfo) clientMessage.getMessageContent();
        if (messageContent.getFirst()) {
            int newId;
            synchronized (AppConfig.idLock) {
                newId = AppConfig.WORKER_ID++;
            }
            AppConfig.info.setWorkerId(newId);
            Message joinMessage = new JoinMessage(newId, -1);
            joinMessage.setMessageContent(messageContent.getNodeInfo());
            MessageUtil.sendMessage(joinMessage, new NodeInfo(AppConfig.BOOTSTRAP.getPort(), AppConfig.BOOTSTRAP.getIpAddress()));
        } else {
            NodeInfoId contactInfo = (NodeInfoId) clientMessage.getMessageContent();
            Message systemKnockMessage = new SystemKnockMessage(-2, contactInfo.getNodeId());
            systemKnockMessage.setMessageContent(contactInfo.getNodeInfo());
            MessageUtil.sendMessage(systemKnockMessage, AppConfig.state.getNodes().get(contactInfo.getNodeId()));
        }
    }
}
