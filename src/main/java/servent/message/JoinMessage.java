package servent.message;

import java.io.Serial;

public class JoinMessage extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 7852113346274336788L;

    public JoinMessage(Integer senderId, Integer receiverId) {
        super(MessageType.JOIN, senderId, receiverId);
    }
}
