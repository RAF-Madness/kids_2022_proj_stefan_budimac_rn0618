package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class JoinMessage extends BasicMessage<NodeInfo> {
    @Serial
    private static final long serialVersionUID = 7852113346274336788L;

    public JoinMessage() {
        this.setType(MessageType.JOIN);
    }

    public JoinMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.JOIN, sender, receiver);
    }
}
