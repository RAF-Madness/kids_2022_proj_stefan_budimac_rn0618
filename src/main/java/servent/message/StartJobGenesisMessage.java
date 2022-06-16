package servent.message;

import app.model.NodeInfo;

import java.io.Serial;

public class StartJobGenesisMessage extends BasicMessage<Object> {
    @Serial
    private static final long serialVersionUID = 475625298345262L;

    public StartJobGenesisMessage() {
        this.setType(MessageType.START_JOB_GENESIS);
    }

    public StartJobGenesisMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.START_JOB_GENESIS, sender, receiver);
    }
}
