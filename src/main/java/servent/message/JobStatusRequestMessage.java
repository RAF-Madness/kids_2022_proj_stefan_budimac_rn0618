package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class JobStatusRequestMessage extends BasicMessage<Object> {
    @Serial
    private static final long serialVersionUID = 111111111111572L;

    public JobStatusRequestMessage() {
        this.setType(MessageType.JOB_STATUS_REQUEST);
    }

    public JobStatusRequestMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.JOB_STATUS_REQUEST, sender, receiver);
    }
}
