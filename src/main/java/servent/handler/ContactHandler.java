package servent.handler;

import app.AppConfig;
import app.ChaosState;
import app.FractalCruncher;
import app.model.NodeInfo;
import servent.message.ContactMessage;
import servent.message.JoinMessage;
import servent.message.SystemKnockMessage;
import servent.message.util.MessageUtil;

public class ContactHandler implements MessageHandler {
    private ContactMessage clientMessage;

    public ContactHandler(ContactMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        NodeInfo messageContent = clientMessage.getPayload();
        if (messageContent.getNodeId().equals(-1)) {
            int newId;
            synchronized (AppConfig.idLock) {
                newId = 0;
            }
            AppConfig.info.setNodeId(newId);
            AppConfig.info.getNodeInfo().setNodeId(newId);
            AppConfig.state.getNodes().put(AppConfig.info.getNodeId(), AppConfig.info.getNodeInfo());
            JoinMessage joinMessage = new JoinMessage(clientMessage.getReceiver(), new NodeInfo(AppConfig.BOOTSTRAP.getPort(), AppConfig.BOOTSTRAP.getIpAddress(), -1, "", ""));
            joinMessage.setPayload(AppConfig.info.getNodeInfo());
            MessageUtil.sendMessage(joinMessage);
        } else {
            SystemKnockMessage systemKnockMessage = new SystemKnockMessage(AppConfig.info.getNodeInfo(), messageContent);
            MessageUtil.sendMessage(systemKnockMessage);
        }
    }
}
