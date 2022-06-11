package servent.message;

import java.io.Serial;

public class WelcomeMessage extends BasicMessage {
    @Serial
    private static final long serialVersionUID = 33388778877887788L;

    public WelcomeMessage(Integer senderId, Integer receiverId) {
        super(MessageType.WELCOME, senderId, receiverId);
    }
}
