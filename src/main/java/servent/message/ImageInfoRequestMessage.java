package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class ImageInfoRequestMessage extends BasicMessage<Object> {
    @Serial
    private static final long serialVersionUID = 35082981899428190L;

    public ImageInfoRequestMessage() {
        this.setType(MessageType.IMAGE_INFO_REQUEST);
    }

    public ImageInfoRequestMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.IMAGE_INFO_REQUEST, sender, receiver);
    }
}
