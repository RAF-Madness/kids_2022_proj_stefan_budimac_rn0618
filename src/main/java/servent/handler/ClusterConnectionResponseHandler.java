package servent.handler;

import servent.message.Message;

public class ClusterConnectionResponseHandler implements MessageHandler {
    private Message clientMessage;

    public ClusterConnectionResponseHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {

    }
}
