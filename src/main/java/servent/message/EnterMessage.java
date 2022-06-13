package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class EnterMessage extends BasicMessage<Integer> {
    @Serial
    private static final long serialVersionUID = 190933234599009L;

    public EnterMessage() {
        this.setType(MessageType.ENTER);
    }

    public EnterMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.ENTER, sender, receiver);
    }
}
