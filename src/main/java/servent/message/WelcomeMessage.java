package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class WelcomeMessage extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 33388778877887788L;

    public WelcomeMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.WELCOME, sender, receiver);
    }
}
