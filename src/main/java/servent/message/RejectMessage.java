package servent.message;

import java.io.Serial;

public class RejectMessage extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 999111111999000555L;

    public RejectMessage(Integer senderId, Integer receiverId) {
        super(MessageType.REJECT, senderId, receiverId);
    }
}
