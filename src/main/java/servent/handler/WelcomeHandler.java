package servent.handler;

import app.AppConfig;
import app.model.NodeInfo;
import app.model.WelcomeContent;
import servent.message.ConnectionRequestMessage;
import servent.message.WelcomeMessage;
import servent.message.util.MessageUtil;

import java.util.Map;

public class WelcomeHandler implements MessageHandler {
    private WelcomeMessage clientMessage;

    public WelcomeHandler(WelcomeMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        WelcomeContent welcomeContent = clientMessage.getPayload();
        AppConfig.info.setNodeId(welcomeContent.getNewId());
        AppConfig.info.getNodeInfo().setNodeId(welcomeContent.getNewId());
        AppConfig.state.getNodes().put(AppConfig.info.getNodeId(), AppConfig.info.getNodeInfo());
        for (Map.Entry<Integer, NodeInfo> entry : welcomeContent.getState().getNodes().entrySet()) {
            AppConfig.state.getNodes().merge(entry.getKey(), entry.getValue(), (nodeInfo, nodeInfo2) -> nodeInfo2);
        }
        synchronized (AppConfig.stateLock) {
            AppConfig.info.setPrevious(clientMessage.getSender());
        }
        ConnectionRequestMessage connectionRequestMessage = new ConnectionRequestMessage(AppConfig.info.getNodeInfo(), AppConfig.state.getFirstNode());
        connectionRequestMessage.setPayload(welcomeContent.getNewId());
        MessageUtil.sendMessage(connectionRequestMessage);
    }
}
