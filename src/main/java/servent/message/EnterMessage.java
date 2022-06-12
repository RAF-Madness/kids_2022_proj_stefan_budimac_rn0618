package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class EnterMessage extends BasicMessage<Integer> {
    @Serial
    private static final long serialVersionUID = 190933234599009L;

    public EnterMessage() {
        this.setMessageType(MessageType.ENTER);
    }

    public EnterMessage(NodeInfo senderInfo, NodeInfo receiverInfo) {
        super(MessageType.ENTER, senderInfo, receiverInfo);
    }
}
