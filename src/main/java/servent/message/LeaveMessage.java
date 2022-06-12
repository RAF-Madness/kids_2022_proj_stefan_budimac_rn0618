package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class LeaveMessage extends BasicMessage<NodeInfo> {
    @Serial
    private static final long serialVersionUID = 1488987547211223633L;

    public LeaveMessage() {
        this.setMessageType(MessageType.LEAVE);
    }

    public LeaveMessage(Integer senderId, Integer receiverId) {
        super(MessageType.LEAVE, senderId, receiverId);
    }
}
