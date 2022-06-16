package servent.handler;

import app.AppConfig;
import app.model.ClusterWelcomeContent;
import servent.message.ClusterKnockMessage;
import servent.message.ClusterWelcomeMessage;
import servent.message.util.MessageUtil;

public class ClusterKnockHandler implements MessageHandler {
    private ClusterKnockMessage clientMessage;

    public ClusterKnockHandler(ClusterKnockMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        ClusterWelcomeMessage clusterWelcomeMessage = new ClusterWelcomeMessage(AppConfig.info.getNodeInfo(), clientMessage.getSender());
        String fractalId = AppConfig.state.generateFractalId();
        clusterWelcomeMessage.setPayload(new ClusterWelcomeContent(fractalId, AppConfig.state.getClusterNodes()));
        MessageUtil.sendMessage(clusterWelcomeMessage);
    }
}
