package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class ClusterKnockMessage extends BasicMessage<Object> {
    @Serial
    private static final long serialVersionUID = 46335768022503852L;

    public ClusterKnockMessage() {
        this.setType(MessageType.CLUSTER_KNOCK);
    }

    public ClusterKnockMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.CLUSTER_KNOCK, sender, receiver);
    }
}
