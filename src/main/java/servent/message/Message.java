package servent.message;

import app.model.NodeInfo;

import java.io.Serializable;

public interface Message<T> extends Serializable {
    NodeInfo getSenderInfo();
    NodeInfo getReceiverInfo();
    MessageType getMessageType();
    T getMessageContent();
    void setMessageContent(T content);
    String toJson();
}
