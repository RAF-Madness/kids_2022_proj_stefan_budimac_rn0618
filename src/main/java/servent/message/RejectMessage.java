package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class RejectMessage extends BasicMessage<NodeInfo> {
    @Serial
    private static final long serialVersionUID = 999111111999000555L;

    public RejectMessage() {
        this.setMessageType(MessageType.REJECT);
    }

    public RejectMessage(Integer senderId, Integer receiverId) {
        super(MessageType.REJECT, senderId, receiverId);
    }
}
