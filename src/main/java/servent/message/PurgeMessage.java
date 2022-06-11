package servent.message;

import java.io.Serial;

public class PurgeMessage extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 8855113311255664422L;

    public PurgeMessage(Integer senderId, Integer receiverId) {
        super(MessageType.PURGE, senderId, receiverId);
    }
}
