package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class SystemKnockMessage extends BasicMessage<Object> {
    @Serial
    private static final long serialVersionUID = 33233211441132256L;

    public SystemKnockMessage() {
        this.setMessageType(MessageType.SYSTEM_KNOCK);
    }

    public SystemKnockMessage(NodeInfo senderInfo, NodeInfo receiverInfo) {
        super(MessageType.SYSTEM_KNOCK, senderInfo, receiverInfo);
    }
}
