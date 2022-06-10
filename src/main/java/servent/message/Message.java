package servent.message;

import java.io.Serializable;
import java.util.List;

public interface Message extends Serializable {
    int getSenderPort();
    int getReceiverPort();
    String getSenderIpAddress();
    String getReceiverIpAddress();
    int getSenderId();
    int getReceiverId();
    MessageType getMessageType();
    List<Object> getMessageContent();
    void setMessageContent(List<Object> content);
    String toJson();
}
