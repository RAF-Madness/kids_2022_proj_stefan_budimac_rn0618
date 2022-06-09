package servent.message;

import app.model.NodeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.Serial;

public abstract class BasicMessage implements Message {
    @Serial
    private static final long serialVersionUID = 33333411141121156L;

    private MessageType type;
    private Object content;
    private NodeInfo sender;
    private NodeInfo receiver;

    public BasicMessage(MessageType type, NodeInfo sender, NodeInfo receiver) {
        this.type = type;
        this.sender = sender;
        this.receiver = receiver;
    }

    public BasicMessage(MessageType type, Object content, NodeInfo sender, NodeInfo receiver) {
        this.type = type;
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    @Override
    public int getSenderPort() {
        return sender.getPort();
    }

    @Override
    public int getReceiverPort() {
        return receiver.getPort();
    }

    @Override
    public String getSenderIpAddress() {
        return sender.getIpAddress();
    }

    @Override
    public String getReceiverIpAddress() {
        return receiver.getIpAddress();
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
