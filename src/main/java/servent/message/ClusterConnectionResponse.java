package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class ClusterConnectionResponse extends BasicMessage<Object> {
    @Serial
    private static final long serialVersionUID = 111090000909934L;

    public ClusterConnectionResponse() {
        this.setType(MessageType.CLUSTER_CONNECTION_RESPONSE);
    }

    public ClusterConnectionResponse(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.CLUSTER_CONNECTION_RESPONSE, sender, receiver);
    }
}
