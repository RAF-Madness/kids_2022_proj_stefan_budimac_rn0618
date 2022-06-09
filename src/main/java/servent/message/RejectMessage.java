package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class RejectMessage extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 999111111999000555L;

    public RejectMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.REJECT, sender, receiver);
    }
}
