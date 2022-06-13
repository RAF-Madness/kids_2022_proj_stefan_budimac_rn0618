package servent.message;

import app.model.NodeInfo;

import java.io.Serializable;
import java.util.List;

public interface Message<T> extends Serializable {
    NodeInfo getSender();
    NodeInfo getReceiver();
    MessageType getType();
    T getPayload();
    void setPayload(T content);
    List<Integer> getRoutes();
    String toJson();
}
