package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class ClusterConnectionResponseMessage extends BasicMessage<Boolean> {
    @Serial
    private static final long serialVersionUID = 111090000909934L;

    public ClusterConnectionResponseMessage() {
        this.setType(MessageType.CLUSTER_CONNECTION_RESPONSE);
    }

    public ClusterConnectionResponseMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.CLUSTER_CONNECTION_RESPONSE, sender, receiver);
    }
}
