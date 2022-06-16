package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class JobStatusMessage extends BasicMessage<Object> {
    @Serial
    private static final long serialVersionUID = 8888853985782157288L;

    public JobStatusMessage() {
        this.setType(MessageType.JOB_STATUS);
    }

    public JobStatusMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.JOB_STATUS, sender, receiver);
    }
}
