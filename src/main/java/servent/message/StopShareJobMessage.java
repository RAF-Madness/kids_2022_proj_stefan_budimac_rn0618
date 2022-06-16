package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class StopShareJobMessage extends BasicMessage<Object> {
    @Serial
    private static final long serialVersionUID = 9958584463662388L;

    public StopShareJobMessage() {
        this.setType(MessageType.STOP_SHARE_JOB);
    }

    public StopShareJobMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.STOP_SHARE_JOB, sender, receiver);
    }
}
