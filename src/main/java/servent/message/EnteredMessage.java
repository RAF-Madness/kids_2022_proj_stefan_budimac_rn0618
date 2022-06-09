package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class EnteredMessage extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 190933234599009L;

    public EnteredMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.ENTERED, sender, receiver);
    }
}
