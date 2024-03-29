package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class PurgeMessage extends BasicMessage<Object> {
    @Serial
    private static final long serialVersionUID = 8855113311255664422L;

    public PurgeMessage() {
        this.setType(MessageType.PURGE);
    }

    public PurgeMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.PURGE, sender, receiver);
    }
}
