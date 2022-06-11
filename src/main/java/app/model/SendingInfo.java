package app.model;

import java.io.Serial;
import java.io.Serializable;

public class SendingInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 29008852111443322L;

    private NodeInfo senderInfo;
    private NodeInfo receiverInfo;

    public SendingInfo(NodeInfo senderInfo, NodeInfo receiverInfo) {
        this.senderInfo = senderInfo;
        this.receiverInfo = receiverInfo;
    }

    public NodeInfo getSenderInfo() {
        return senderInfo;
    }

    public NodeInfo getReceiverInfo() {
        return receiverInfo;
    }
}
