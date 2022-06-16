package servent.handler;

import app.AppConfig;
import app.model.ClusterWelcomeContent;
import app.model.NodeInfo;
import servent.message.ClusterConnectionRequestMessage;
import servent.message.ClusterWelcomeMessage;
import servent.message.EnteredClusterMessage;
import servent.message.util.MessageUtil;

import java.util.Map;

public class ClusterWelcomeHandler implements MessageHandler {
    private ClusterWelcomeMessage clientMessage;

    public ClusterWelcomeHandler(ClusterWelcomeMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        ClusterWelcomeContent content = clientMessage.getPayload();
        AppConfig.info.setFractalId(content.getFractalId());
        AppConfig.state.setClusterNodes(content.getClusterNodes());
        for (Map.Entry<Integer, NodeInfo> entry : AppConfig.state.getClusterNodes().entrySet()) {
            EnteredClusterMessage enteredClusterMessage = new EnteredClusterMessage(AppConfig.info.getNodeInfo(), entry.getValue());
            MessageUtil.sendMessage(enteredClusterMessage);
        }
        for (Map.Entry<Integer, NodeInfo> entry : AppConfig.state.isDifferenceInOne(AppConfig.info.getFractalId()).entrySet()) {
            ClusterConnectionRequestMessage clusterConnectionRequestMessage = new ClusterConnectionRequestMessage(AppConfig.info.getNodeInfo(), entry.getValue());
            MessageUtil.sendMessage(clusterConnectionRequestMessage);
        }
    }
}
