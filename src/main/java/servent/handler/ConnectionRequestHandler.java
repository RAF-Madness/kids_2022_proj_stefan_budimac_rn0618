package servent.handler;

import app.AppConfig;
import app.model.NodeInfo;
import servent.message.ConnectionResponseMessage;
import servent.message.Message;
import servent.message.util.MessageUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConnectionRequestHandler implements MessageHandler {
    private Message clientMessage;

    public ConnectionRequestHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        NodeInfo newNodeInfo = (NodeInfo) clientMessage.getMessageContent();
        Message connectionResponseMessage = new ConnectionResponseMessage(AppConfig.info.getWorkerId(), clientMessage.getSenderId());
        synchronized (AppConfig.stateLock) {
            AppConfig.state.setPrevious(newNodeInfo);
        }
        try {
            NodeInfo firstNode = new NodeInfo(AppConfig.info.getPort(), InetAddress.getLocalHost().getHostAddress());
            connectionResponseMessage.setMessageContent(firstNode);
            MessageUtil.sendMessage(connectionResponseMessage, AppConfig.state.getNodes().get(clientMessage.getSenderId()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
