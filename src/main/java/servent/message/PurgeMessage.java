package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class PurgeMessage extends BasicMessage<NodeInfo> {
    @Serial
    private static final long serialVersionUID = 8855113311255664422L;

    public PurgeMessage() {
        this.setMessageType(MessageType.PURGE);
    }

    public PurgeMessage(Integer senderId, Integer receiverId) {
        super(MessageType.PURGE, senderId, receiverId);
    }
}
