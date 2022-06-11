package servent.message;

import java.io.Serial;

public class HailMessage extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 1234123443214321555L;

    public HailMessage(Integer senderId, Integer receiverId) {
        super(MessageType.HAIL, senderId, receiverId);
    }
}
