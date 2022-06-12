package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class JoinMessage extends BasicMessage<Integer> {
    @Serial
    private static final long serialVersionUID = 7852113346274336788L;

    public JoinMessage() {
        this.setMessageType(MessageType.JOIN);
    }

    public JoinMessage(NodeInfo senderInfo, NodeInfo receiverInfo) {
        super(MessageType.JOIN, senderInfo, receiverInfo);
    }
}
