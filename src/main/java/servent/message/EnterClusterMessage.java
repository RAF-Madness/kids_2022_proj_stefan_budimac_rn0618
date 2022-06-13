package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class EnterClusterMessage extends BasicMessage<Object> {
    @Serial
    private static final long serialVersionUID = 34483958235820385L;

    public EnterClusterMessage() {
        this.setType(MessageType.ENTERED_CLUSTER);
    }

    public EnterClusterMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.ENTERED_CLUSTER, sender, receiver);
    }
}
