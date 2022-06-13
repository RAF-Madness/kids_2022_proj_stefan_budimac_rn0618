package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class ClusterConnectionRequest extends BasicMessage<Object> {
    @Serial
    private static final long serialVersionUID = 1231249403863940994L;

    public ClusterConnectionRequest() {
        this.setType(MessageType.CLUSTER_CONNECTION_REQUEST);
    }

    public ClusterConnectionRequest(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.CLUSTER_CONNECTION_REQUEST, sender, receiver);
    }
}
