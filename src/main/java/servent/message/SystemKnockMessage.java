package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class SystemKnockMessage extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 33233211441132256L;

    public SystemKnockMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.SYSTEM_KNOCK, sender, receiver);
    }
}
