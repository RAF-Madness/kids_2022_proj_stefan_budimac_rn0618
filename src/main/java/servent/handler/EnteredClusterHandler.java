package servent.handler;

import servent.message.EnteredClusterMessage;

public class EnteredClusterHandler implements MessageHandler {
    private EnteredClusterMessage clientMessage;

    public EnteredClusterHandler(EnteredClusterMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {

    }
}
