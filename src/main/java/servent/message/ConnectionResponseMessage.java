package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class ConnectionResponseMessage extends BasicMessage<Integer> {
    @Serial
    private static final long serialVersionUID = 74589488599604310L;

    public ConnectionResponseMessage() {
        this.setMessageType(MessageType.CONNECTION_RESPONSE);
    }

    public ConnectionResponseMessage(NodeInfo senderInfo, NodeInfo receiverInfo) {
        super(MessageType.CONNECTION_RESPONSE, senderInfo, receiverInfo);
    }
}
