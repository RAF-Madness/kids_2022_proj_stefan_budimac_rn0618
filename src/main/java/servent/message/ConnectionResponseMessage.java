package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class ConnectionResponseMessage extends BasicMessage<NodeInfo> {
    @Serial
    private static final long serialVersionUID = 74589488599604310L;

    public ConnectionResponseMessage() {
        this.setMessageType(MessageType.CONNECTION_RESPONSE);
    }

    public ConnectionResponseMessage(Integer senderId, Integer receiverId) {
        super(MessageType.CONNECTION_RESPONSE, senderId, receiverId);
    }
}
