package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class ConnectionRequestMessage extends BasicMessage<Integer> {
    @Serial
    private static final long serialVersionUID = 1117771111177777L;

    public ConnectionRequestMessage() {
        this.setMessageType(MessageType.CONNECTION_REQUEST);
    }

    public ConnectionRequestMessage(NodeInfo senderInfo, NodeInfo receiverInfo) {
        super(MessageType.CONNECTION_REQUEST, senderInfo, receiverInfo);
    }
}
