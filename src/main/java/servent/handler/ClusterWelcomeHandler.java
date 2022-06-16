package servent.handler;

import servent.message.ClusterWelcomeMessage;

public class ClusterWelcomeHandler implements MessageHandler {
    private ClusterWelcomeMessage clientMessage;

    public ClusterWelcomeHandler(ClusterWelcomeMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {

    }
}
