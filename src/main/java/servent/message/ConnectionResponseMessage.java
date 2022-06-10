package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class ConnectionResponseMessage extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 74589488599604310L;

    public ConnectionResponseMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.CONNECTION_RESPONSE, sender, receiver);
    }
}
