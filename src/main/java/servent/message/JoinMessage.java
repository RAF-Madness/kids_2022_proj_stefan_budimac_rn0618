package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class JoinMessage extends BasicMessage<NodeInfo> {
    @Serial
    private static final long serialVersionUID = 7852113346274336788L;

    public JoinMessage() {
        this.setMessageType(MessageType.JOIN);
    }

    public JoinMessage(Integer senderId, Integer receiverId) {
        super(MessageType.JOIN, senderId, receiverId);
    }
}
