package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class HailMessage extends BasicMessage<Object> {
    @Serial
    private static final long serialVersionUID = 1234123443214321555L;

    public HailMessage() {
        this.setType(MessageType.HAIL);
    }

    public HailMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.HAIL, sender, receiver);
    }
}
