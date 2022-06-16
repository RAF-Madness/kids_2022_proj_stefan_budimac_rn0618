package servent.handler;

import app.AppConfig;
import servent.message.ClusterConnectionRequestMessage;
import servent.message.ClusterConnectionResponseMessage;
import servent.message.util.MessageUtil;

public class ClusterConnectionRequestHandler implements MessageHandler {
    private ClusterConnectionRequestMessage clientMessage;

    public ClusterConnectionRequestHandler(ClusterConnectionRequestMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        ClusterConnectionResponseMessage clusterConnectionResponseMessage = new ClusterConnectionResponseMessage(clientMessage.getReceiver(), clientMessage.getSender());
        clusterConnectionResponseMessage.setPayload(Boolean.TRUE);
        AppConfig.state.getClusterNeighbours().put(clientMessage.getSender().getNodeId(), clientMessage.getSender());
        MessageUtil.sendMessage(clusterConnectionResponseMessage);
    }
}
