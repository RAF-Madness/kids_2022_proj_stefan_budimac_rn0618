package servent.handler;

import servent.message.ClusterConnectionRequestMessage;

public class ClusterConnectionRequestHandler implements MessageHandler {
    private ClusterConnectionRequestMessage clientMessage;

    public ClusterConnectionRequestHandler(ClusterConnectionRequestMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {

    }
}
