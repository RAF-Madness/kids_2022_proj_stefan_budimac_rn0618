package servent.message;

import java.io.Serializable;

public interface Message extends Serializable {
    int getSenderPort();
    int getRecieverPort();
    String getSenderIpAddress();
    String getRecieverIpAddress();
    MessageType getMessageType();
    String getMessageText();
    String toJson();
}
