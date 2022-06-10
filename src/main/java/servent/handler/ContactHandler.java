package servent.handler;

import app.AppConfig;
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
        Boolean first = (Boolean) clientMessage.getMessageContent().get(0);
        if (first) {
            NodeInfo bootstrapInfo = new NodeInfo(AppConfig.info.getBootstrapPort(), AppConfig.info.getBootstrapIpAddress(), -1);
            int newId;
            synchronized (AppConfig.idLock) {
                newId = AppConfig.WORKER_ID++;
            }
            AppConfig.info.setWorkerId(newId);
            Message joinMessage = new JoinMessage(AppConfig.info.getNodeInfo(), bootstrapInfo);
            MessageUtil.sendMessage(joinMessage);
        } else {
            Message systemKnockMessage = new SystemKnockMessage(AppConfig.info.getNodeInfo(), (NodeInfo) clientMessage.getMessageContent().get(1));
            MessageUtil.sendMessage(systemKnockMessage);
        }
    }
}
