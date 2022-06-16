package servent.handler;

import servent.message.ClusterKnockMessage;

public class ClusterKnockHandler implements MessageHandler {
    private ClusterKnockMessage clientMessage;

    public ClusterKnockHandler(ClusterKnockMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {

    }
}
