package servent.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.Serial;
import java.util.List;

public abstract class BasicMessage implements Message {
    @Serial
    private static final long serialVersionUID = 33333411141121156L;

    private MessageType type;
    private Object content;
    private Integer senderId;
    private Integer receiverId;

    public BasicMessage(MessageType type, Integer senderId, Integer receiverId) {
        this.type = type;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public BasicMessage(MessageType type, List<Object> content, Integer senderId, Integer receiverId) {
        this.type = type;
        this.content = content;
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
        return type;
    }

    @Override
    public Object getMessageContent() {
        return content;
    }

    @Override
    public void setMessageContent(Object content) {
        this.content = content;
    }

    @Override
    public String toJson() {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return objectWriter.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
