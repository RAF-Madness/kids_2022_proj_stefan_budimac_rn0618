package servent.handler;

import servent.message.Message;

public class ClusterKnockHandler implements MessageHandler {
    private Message clientMessage;

    public ClusterKnockHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {

    }
}
