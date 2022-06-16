package servent.handler;

import servent.message.ApproachClusterMessage;

public class ApproachClusterHandler implements MessageHandler {
    private ApproachClusterMessage clientMessage;

    public ApproachClusterHandler(ApproachClusterMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {

    }
}
