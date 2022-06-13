package servent.handler;

import servent.message.Message;

public class EnteredClusterHandler implements MessageHandler {
    private Message clientMessage;

    public EnteredClusterHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {

    }
}
