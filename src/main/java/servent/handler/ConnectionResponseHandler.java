package servent.handler;

import app.AppConfig;
import app.model.NodeInfo;
import servent.message.EnterMessage;
import servent.message.JoinMessage;
import servent.message.Message;
import servent.message.util.MessageUtil;

import java.util.Map;

public class ConnectionResponseHandler implements MessageHandler {
    private Message clientMessage;

    public ConnectionResponseHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        synchronized (AppConfig.stateLock) {
            AppConfig.state.setNext(clientMessage.getSender());
        }
        for (Map.Entry<Integer, NodeInfo> entry : AppConfig.state.getNodes().entrySet()) {
            if (entry.getKey().equals(AppConfig.info.getNodeId())) {
                continue;
            }
            Message enterMessage = new EnterMessage(AppConfig.info.getNodeInfo(), entry.getValue());
            enterMessage.setPayload(clientMessage.getPayload());
            MessageUtil.sendMessage(enterMessage);
        }
        Message joinMessage = new JoinMessage(AppConfig.info.getNodeInfo(), new NodeInfo(AppConfig.BOOTSTRAP.getPort(), AppConfig.BOOTSTRAP.getIpAddress(), -1, "", ""));
        joinMessage.setPayload(AppConfig.info.getNodeId());
        MessageUtil.sendMessage(joinMessage);
    }
}
