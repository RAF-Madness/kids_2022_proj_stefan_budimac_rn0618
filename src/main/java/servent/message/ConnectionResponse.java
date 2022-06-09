package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class ConnectionResponse extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 74589488599604310L;

    public ConnectionResponse(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.CONNECTION_RESPONSE, sender, receiver);
    }
}
