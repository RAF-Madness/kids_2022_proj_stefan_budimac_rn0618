package servent.message;

import java.io.Serializable;

public interface Message<T> extends Serializable {
    Integer getSenderId();
    Integer getReceiverId();
    MessageType getMessageType();
    T getMessageContent();
    void setMessageContent(T content);
    String toJson();
}
