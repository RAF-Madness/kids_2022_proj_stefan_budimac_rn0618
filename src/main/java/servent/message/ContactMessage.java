package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class ContactMessage extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 1441441441223355555L;

    public ContactMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.CONTACT, sender, receiver);
    }
}
