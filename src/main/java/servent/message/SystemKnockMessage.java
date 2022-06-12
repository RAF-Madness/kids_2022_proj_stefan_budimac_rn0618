package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class SystemKnockMessage extends BasicMessage<NodeInfo> {
    @Serial
    private static final long serialVersionUID = 33233211441132256L;

    public SystemKnockMessage() {
        this.setMessageType(MessageType.SYSTEM_KNOCK);
    }

    public SystemKnockMessage(Integer senderId, Integer receiverId) {
        super(MessageType.SYSTEM_KNOCK, senderId, receiverId);
    }
}
