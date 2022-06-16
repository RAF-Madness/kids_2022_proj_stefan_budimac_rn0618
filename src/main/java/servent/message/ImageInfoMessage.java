package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class ImageInfoMessage extends BasicMessage<Object> {
    @Serial
    private static final long serialVersionUID = 2847134237427422L;

    public ImageInfoMessage() {
        this.setType(MessageType.IMAGE_INFO);
    }

    public ImageInfoMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.IMAGE_INFO, sender, receiver);
    }
}
