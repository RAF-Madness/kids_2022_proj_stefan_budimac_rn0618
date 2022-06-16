package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class ApproachClusterMessage extends BasicMessage<NodeInfo> {
    @Serial
    private static final long serialVersionUID = 123444213121234L;

    public ApproachClusterMessage() {
        this.setType(MessageType.APPROACH_CLUSTER);
    }

    public ApproachClusterMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.APPROACH_CLUSTER, sender, receiver);
    }
}
