package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class LeaveMessage extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 1488987547211223633L;

    public LeaveMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.LEAVE, sender, receiver);
    }
}
