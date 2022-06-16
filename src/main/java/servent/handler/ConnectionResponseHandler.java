package servent.handler;

import app.AppConfig;
import app.model.NodeInfo;
import servent.message.ConnectionResponseMessage;
import servent.message.EnterMessage;
import servent.message.JoinMessage;
import servent.message.util.MessageUtil;

import java.util.Map;

public class ConnectionResponseHandler implements MessageHandler {
    private ConnectionResponseMessage clientMessage;

    public ConnectionResponseHandler(ConnectionResponseMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        synchronized (AppConfig.stateLock) {
            AppConfig.info.setNext(clientMessage.getSender());
        }
        for (Map.Entry<Integer, NodeInfo> entry : AppConfig.state.getNodes().entrySet()) {
            if (entry.getKey().equals(AppConfig.info.getNodeId())) {
                continue;
            }
            EnterMessage enterMessage = new EnterMessage(AppConfig.info.getNodeInfo(), entry.getValue());
            MessageUtil.sendMessage(enterMessage);
        }
        JoinMessage joinMessage = new JoinMessage(AppConfig.info.getNodeInfo(), new NodeInfo(AppConfig.BOOTSTRAP.getPort(), AppConfig.BOOTSTRAP.getIpAddress(), -1, "", ""));
        joinMessage.setPayload(AppConfig.info.getNodeInfo());
        MessageUtil.sendMessage(joinMessage);
    }
}
