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
            AppConfig.state.setNext(clientMessage.getSenderInfo());
        }
        for (Map.Entry<Integer, NodeInfo> entry : AppConfig.state.getNodes().entrySet()) {
            if (entry.getKey().equals(AppConfig.info.getWorkerId())) {
                continue;
            }
            Message enterMessage = new EnterMessage(AppConfig.info.getNodeInfo(), entry.getValue());
            enterMessage.setMessageContent(clientMessage.getMessageContent());
            MessageUtil.sendMessage(enterMessage);
        }
        Message joinMessage = new JoinMessage(AppConfig.info.getNodeInfo(), new NodeInfo(AppConfig.BOOTSTRAP.getPort(), AppConfig.BOOTSTRAP.getIpAddress()));
        joinMessage.setMessageContent(AppConfig.info.getWorkerId());
        MessageUtil.sendMessage(joinMessage);
    }
}
