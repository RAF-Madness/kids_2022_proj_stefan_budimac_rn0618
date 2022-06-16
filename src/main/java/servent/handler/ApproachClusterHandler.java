package servent.handler;

import app.AppConfig;
import app.model.NodeInfo;
import servent.message.ApproachClusterMessage;
import servent.message.ClusterKnockMessage;
import servent.message.util.MessageUtil;

public class ApproachClusterHandler implements MessageHandler {
    private ApproachClusterMessage clientMessage;

    public ApproachClusterHandler(ApproachClusterMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        NodeInfo contact = clientMessage.getPayload();
        ClusterKnockMessage clusterKnockMessage = new ClusterKnockMessage(AppConfig.info.getNodeInfo(), contact);
        MessageUtil.sendMessage(clusterKnockMessage);
    }
}
