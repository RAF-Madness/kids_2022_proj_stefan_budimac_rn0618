package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class ClusterWelcomeMessage extends BasicMessage<Object> {
    @Serial
    private static final long serialVersionUID = 9964406590440358L;

    public ClusterWelcomeMessage() {
        this.setType(MessageType.CLUSTER_WELCOME);
    }

    public ClusterWelcomeMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.CLUSTER_WELCOME, sender, receiver);
    }
}
