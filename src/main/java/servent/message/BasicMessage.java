package servent.message;

import app.model.NodeInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import servent.message.util.MessageDeserializer;

import java.io.Serial;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class BasicMessage<T> implements Message<T> {
    @Serial
    private static final long serialVersionUID = 33333411141121156L;

    protected static MessageDeserializer messageDeserializer = new MessageDeserializer("type");

    private MessageType type;
    private T payload;
    private NodeInfo sender;
    private NodeInfo receiver;
    private List<Integer> routes;

    public BasicMessage() {}

    public BasicMessage(MessageType type, NodeInfo sender, NodeInfo receiver) {
        this.type = type;
        this.sender = sender;
        this.receiver = receiver;
        this.routes = new CopyOnWriteArrayList<>();
    }

    public BasicMessage(MessageType type, T payload, NodeInfo sender, NodeInfo receiver) {
        this.type = type;
        this.payload = payload;
        this.sender = sender;
        this.receiver = receiver;
        this.routes = new CopyOnWriteArrayList<>();
    }

    @Override
    public NodeInfo getSender() {
        return sender;
    }

    public void setSender(NodeInfo sender) {
        this.sender = sender;
    }

    @Override
    public NodeInfo getReceiver() {
        return receiver;
    }

    public void setReceiver(NodeInfo receiver) {
        this.receiver = receiver;
    }

    @Override
    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    @Override
    public T getPayload() {
        return payload;
    }

    @Override
    public void setPayload(T payload) {
        this.payload = payload;
    }

    @Override
    public List<Integer> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Integer> routes) {
        this.routes = routes;
    }

    @Override
    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public static Message<?> fromJson(String json) {
        return messageDeserializer.deserialize(json);
    }
}
