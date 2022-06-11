package servent.message;

import java.io.Serial;

public class ContactMessage extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 1441441441223355555L;

    public ContactMessage(Integer senderId, Integer receiverId) {
        super(MessageType.CONTACT, senderId, receiverId);
    }
}
