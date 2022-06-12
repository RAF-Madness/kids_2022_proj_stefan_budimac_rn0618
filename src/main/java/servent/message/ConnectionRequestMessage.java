package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class ConnectionRequestMessage extends BasicMessage<NodeInfo> {
    @Serial
    private static final long serialVersionUID = 1117771111177777L;

    public ConnectionRequestMessage() {
        this.setMessageType(MessageType.CONNECTION_REQUEST);
    }

    public ConnectionRequestMessage(Integer senderId, Integer receiverId) {
        super(MessageType.CONNECTION_REQUEST, senderId, receiverId);
    }
}
