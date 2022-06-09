package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class ConnectionRequest extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 1117771111177777L;

    public ConnectionRequest(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.CONNECTION_REQUEST, sender, receiver);
    }
}
