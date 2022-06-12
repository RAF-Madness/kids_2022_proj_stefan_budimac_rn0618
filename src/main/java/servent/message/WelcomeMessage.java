package servent.message;

import app.model.WelcomeContent;
import app.model.NodeInfo;

import java.io.Serial;

public class WelcomeMessage extends BasicMessage<WelcomeContent> {
    @Serial
    private static final long serialVersionUID = 33388778877887788L;

    public WelcomeMessage() {
        this.setMessageType(MessageType.WELCOME);
    }

    public WelcomeMessage(NodeInfo senderInfo, NodeInfo receiverInfo) {
        super(MessageType.WELCOME, senderInfo, receiverInfo);
    }
}
