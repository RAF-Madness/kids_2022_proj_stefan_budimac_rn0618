package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class PurgeMessage extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 8855113311255664422L;

    public PurgeMessage(MessageType type, NodeInfo sender, NodeInfo receiver) {
        super(type, sender, receiver);
    }
}
