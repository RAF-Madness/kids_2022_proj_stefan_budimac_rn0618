package servent.message;

import java.io.Serial;

public class ConnectionRequestMessage extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 1117771111177777L;

    public ConnectionRequestMessage(Integer senderId, Integer receiverId) {
        super(MessageType.CONNECTION_REQUEST, senderId, receiverId);
    }
}
