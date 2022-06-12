package servent.message;

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
    private Integer senderId;
    private Integer receiverId;

    public BasicMessage() {}

    public BasicMessage(MessageType messageType, Integer senderId, Integer receiverId) {
        this.messageType = messageType;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public BasicMessage(MessageType messageType, T messageContent, Integer senderId, Integer receiverId) {
        this.messageType = messageType;
        this.messageContent = messageContent;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    @Override
    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    @Override
    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
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
