package servent.message;

import app.model.KnockAnswer;

import java.io.Serial;

public class WelcomeMessage extends BasicMessage<KnockAnswer> {
    @Serial
    private static final long serialVersionUID = 33388778877887788L;

    public WelcomeMessage() {
        this.setMessageType(MessageType.WELCOME);
    }

    public WelcomeMessage(Integer senderId, Integer receiverId) {
        super(MessageType.WELCOME, senderId, receiverId);
    }
}
