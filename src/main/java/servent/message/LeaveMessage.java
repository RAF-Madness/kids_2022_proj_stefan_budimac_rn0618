package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class LeaveMessage extends BasicMessage<Object> {
    @Serial
    private static final long serialVersionUID = 1488987547211223633L;

    public LeaveMessage() {
        this.setType(MessageType.LEAVE);
    }

    public LeaveMessage(NodeInfo senedr, NodeInfo receiver) {
        super(MessageType.LEAVE, senedr, receiver);
    }
}
