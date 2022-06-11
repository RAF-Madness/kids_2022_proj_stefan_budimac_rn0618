package servent.message;

import java.io.Serial;

public class EnterMessage extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 190933234599009L;

    public EnterMessage(Integer senderId, Integer receiverId) {
        super(MessageType.ENTER, senderId, receiverId);
    }
}
