package servent.handler;

import app.AppConfig;
import servent.message.ClusterConnectionResponseMessage;

public class ClusterConnectionResponseHandler implements MessageHandler {
    private ClusterConnectionResponseMessage clientMessage;

    public ClusterConnectionResponseHandler(ClusterConnectionResponseMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        AppConfig.state.getClusterNeighbours().put(clientMessage.getSender().getNodeId(), clientMessage.getSender());
    }
}
