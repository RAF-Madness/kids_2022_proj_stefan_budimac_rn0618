package servent.message;

import java.io.Serial;

public class LeaveMessage extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 1488987547211223633L;

    public LeaveMessage(Integer senderId, Integer receiverId) {
        super(MessageType.LEAVE, senderId, receiverId);
    }
}
