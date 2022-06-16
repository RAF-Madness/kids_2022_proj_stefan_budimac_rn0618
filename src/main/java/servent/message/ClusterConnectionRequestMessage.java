package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class ClusterConnectionRequestMessage extends BasicMessage<Object> {
    @Serial
    private static final long serialVersionUID = 1231249403863940994L;

    public ClusterConnectionRequestMessage() {
        this.setType(MessageType.CLUSTER_CONNECTION_REQUEST);
    }

    public ClusterConnectionRequestMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.CLUSTER_CONNECTION_REQUEST, sender, receiver);
    }
}
