package servent.message;

import java.io.Serializable;

public interface Message extends Serializable {
    Integer getSenderId();
    Integer getReceiverId();
    MessageType getMessageType();
    Object getMessageContent();
    void setMessageContent(Object content);
    String toJson();
}
