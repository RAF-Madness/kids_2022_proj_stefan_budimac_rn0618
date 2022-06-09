package servent.message;

import java.io.Serializable;

public interface Message extends Serializable {
    int getSenderPort();
    int getReceiverPort();
    String getSenderIpAddress();
    String getReceiverIpAddress();
    MessageType getMessageType();
    Object getMessageContent();
    void setMessageContent(Object content);
    String toJson();
}
