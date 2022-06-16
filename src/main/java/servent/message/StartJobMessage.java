package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class StartJobMessage extends BasicMessage<Object> {
    @Serial
    private static final long serialVersionUID = 77756666636322112L;

    public StartJobMessage() {
        this.setType(MessageType.START_JOB);
    }

    public StartJobMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.START_JOB, sender, receiver);
    }
}
