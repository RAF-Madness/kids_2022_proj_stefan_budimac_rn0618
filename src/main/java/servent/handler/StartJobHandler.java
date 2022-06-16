package servent.handler;

import servent.message.StartJobMessage;

public class StartJobHandler implements MessageHandler {
    private StartJobMessage clientMessage;

    public StartJobHandler(StartJobMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {

    }
}
