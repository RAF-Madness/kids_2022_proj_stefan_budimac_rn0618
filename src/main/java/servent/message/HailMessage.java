package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class HailMessage extends BasicMessage<NodeInfo> {
    @Serial
    private static final long serialVersionUID = 1234123443214321555L;

    public HailMessage() {
        this.setMessageType(MessageType.HAIL);
    }

    public HailMessage(Integer senderId, Integer receiverId) {
        super(MessageType.HAIL, senderId, receiverId);
    }
}
