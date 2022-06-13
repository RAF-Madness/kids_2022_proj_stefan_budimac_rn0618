package servent.handler;

import servent.message.Message;

public class ClusterWelcomeHandler implements MessageHandler {
    private Message clientMessage;

    public ClusterWelcomeHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {

    }
}
