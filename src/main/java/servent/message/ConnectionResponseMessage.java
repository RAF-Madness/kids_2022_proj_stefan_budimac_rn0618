package servent.message;

import java.io.Serial;

public class ConnectionResponseMessage extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 74589488599604310L;

    public ConnectionResponseMessage(Integer senderId, Integer receiverId) {
        super(MessageType.CONNECTION_RESPONSE, senderId, receiverId);
    }
}
