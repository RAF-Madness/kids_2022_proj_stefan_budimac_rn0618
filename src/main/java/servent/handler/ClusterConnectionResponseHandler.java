package servent.handler;

import servent.message.ClusterConnectionResponseMessage;

public class ClusterConnectionResponseHandler implements MessageHandler {
    private ClusterConnectionResponseMessage clientMessage;

    public ClusterConnectionResponseHandler(ClusterConnectionResponseMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {

    }
}
