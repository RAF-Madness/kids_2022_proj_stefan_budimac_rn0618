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
        NodeInfo firstNode = (NodeInfo) clientMessage.getMessageContent();
        synchronized (AppConfig.stateLock) {
            AppConfig.state.setNext(firstNode);
        }
        for (Map.Entry<Integer, NodeInfo> entry : AppConfig.state.getNodes().entrySet()) {
            Message enteredMessage = new EnterMessage(AppConfig.info.getWorkerId(), entry.getKey());
            enteredMessage.setMessageContent(AppConfig.info.getNodeInfo());
            MessageUtil.sendMessage(enteredMessage, entry.getValue());
        }
        Message joinMessage = new JoinMessage(clientMessage.getReceiverId(), -1);
        joinMessage.setMessageContent(AppConfig.info.getNodeInfo());
        MessageUtil.sendMessage(joinMessage, new NodeInfo(AppConfig.BOOTSTRAP.getPort(), AppConfig.BOOTSTRAP.getIpAddress()));
    }
}
