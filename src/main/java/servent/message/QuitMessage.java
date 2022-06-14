package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class QuitMessage extends BasicMessage<NodeInfo> {
    @Serial
    private static final long serialVersionUID = 75777837483798572L;

    public QuitMessage() {
        this.setType(MessageType.QUIT);
    }

    public QuitMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.QUIT, sender, receiver);
    }
}
