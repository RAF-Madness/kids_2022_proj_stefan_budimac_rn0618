package servent.message;

import app.model.ContactContent;
import app.model.NodeInfo;

import java.io.Serial;

public class ContactMessage extends BasicMessage<ContactContent> {
    @Serial
    private static final long serialVersionUID = 1441441441223355555L;

    public ContactMessage() {
        this.setMessageType(MessageType.CONTACT);
    }

    public ContactMessage(NodeInfo senderInfo, NodeInfo receiverInfo) {
        super(MessageType.CONTACT, senderInfo, receiverInfo);
    }
}
