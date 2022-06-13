package servent.handler;

import servent.message.Message;

public class ClusterConnectionRequestHandler implements MessageHandler {
    private Message clientMessage;

    public ClusterConnectionRequestHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {

    }
}
