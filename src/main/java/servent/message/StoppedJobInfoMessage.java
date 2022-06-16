package servent.message;

import app.model.Job;
import app.model.NodeInfo;
import app.model.Result;

import java.io.Serial;

public class StoppedJobInfoMessage extends BasicMessage<Result> {
    @Serial
    private static final long serialVersionUID = 9000488387271761L;

    public StoppedJobInfoMessage() {
        this.setType(MessageType.STOPPED_JOB_INFO);
    }

    public StoppedJobInfoMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.STOPPED_JOB_INFO, sender, receiver);
    }
}
