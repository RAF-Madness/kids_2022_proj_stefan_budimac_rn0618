package servent.message;

import app.model.FirstNodeInfo;
import java.io.Serial;

public class ContactMessage extends BasicMessage<FirstNodeInfo> {
    @Serial
    private static final long serialVersionUID = 1441441441223355555L;

    public ContactMessage() {
        this.setMessageType(MessageType.CONTACT);
    }

    public ContactMessage(Integer senderId, Integer receiverId) {
        super(MessageType.CONTACT, senderId, receiverId);
    }
}
