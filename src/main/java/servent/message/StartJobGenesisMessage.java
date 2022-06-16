package servent.message;

import app.model.GenesisContent;
import app.model.NodeInfo;
import app.model.Result;

import java.io.Serial;

public class StartJobGenesisMessage extends BasicMessage<GenesisContent> {
    @Serial
    private static final long serialVersionUID = 475625298345262L;

    public StartJobGenesisMessage() {
        this.setType(MessageType.START_JOB_GENESIS);
    }

    public StartJobGenesisMessage(NodeInfo sender, NodeInfo receiver) {
        super(MessageType.START_JOB_GENESIS, sender, receiver);
    }
}
