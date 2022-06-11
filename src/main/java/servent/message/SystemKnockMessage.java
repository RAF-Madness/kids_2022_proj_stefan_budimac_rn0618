package servent.message;

import java.io.Serial;

public class SystemKnockMessage extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 33233211441132256L;

    public SystemKnockMessage(Integer senderId, Integer receiverId) {
        super(MessageType.SYSTEM_KNOCK, senderId, receiverId);
    }
}
