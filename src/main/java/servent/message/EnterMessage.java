package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class EnterMessage extends BasicMessage<NodeInfo> {
    @Serial
    private static final long serialVersionUID = 190933234599009L;

    public EnterMessage() {
        this.setMessageType(MessageType.ENTER);
    }

    public EnterMessage(Integer senderId, Integer receiverId) {
        super(MessageType.ENTER, senderId, receiverId);
    }
}
