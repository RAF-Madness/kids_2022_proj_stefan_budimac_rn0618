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
        NodeInfo firstNode = new NodeInfo(clientMessage.getSenderPort(), clientMessage.getSenderIpAddress(), clientMessage.getSenderId());
        synchronized (AppConfig.stateLock) {
            AppConfig.state.setNext(firstNode);
        }
        for (Map.Entry<Integer, NodeInfo> entry : AppConfig.state.getNodes().entrySet()) {
            NodeInfo receiver = entry.getValue();
            Message enteredMessage = new EnterMessage((NodeInfo) clientMessage.getMessageContent().get(0), receiver);
            enteredMessage.getMessageContent().add(AppConfig.info.getNodeInfo().getId());
            MessageUtil.sendMessage(enteredMessage);
        }
        NodeInfo bootstrapInfo = new NodeInfo(AppConfig.info.getBootstrapPort(), AppConfig.info.getBootstrapIpAddress(), -1);
        Message joinMessage = new JoinMessage((NodeInfo) clientMessage.getMessageContent().get(0), bootstrapInfo);
        joinMessage.getMessageContent().add(AppConfig.info.getNodeInfo().getId());
        MessageUtil.sendMessage(joinMessage);
    }
}
