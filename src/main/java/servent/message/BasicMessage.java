package servent.message;

import app.model.NodeInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import servent.message.util.MessageDeserializer;

import java.io.Serial;

public abstract class BasicMessage<T> implements Message<T> {
    @Serial
    private static final long serialVersionUID = 33333411141121156L;

    protected static MessageDeserializer messageDeserializer = new MessageDeserializer("messageType");

    private MessageType messageType;
    private T messageContent;
    private NodeInfo senderInfo;
    private NodeInfo receiverInfo;

    public BasicMessage() {}

    public BasicMessage(MessageType messageType, NodeInfo senderInfo, NodeInfo receiverInfo) {
        this.messageType = messageType;
        this.senderInfo = senderInfo;
        this.receiverInfo = receiverInfo;
    }

    public BasicMessage(MessageType messageType, T messageContent, NodeInfo senderInfo, NodeInfo receiverInfo) {
        this.messageType = messageType;
        this.messageContent = messageContent;
        this.senderInfo = senderInfo;
        this.receiverInfo = receiverInfo;
    }

    @Override
    public NodeInfo getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(NodeInfo senderInfo) {
        this.senderInfo = senderInfo;
    }

    @Override
    public NodeInfo getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(NodeInfo receiverInfo) {
        this.receiverInfo = receiverInfo;
    }

    @Override
    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @Override
    public T getMessageContent() {
        return messageContent;
    }

    @Override
    public void setMessageContent(T messageContent) {
        this.messageContent = messageContent;
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
