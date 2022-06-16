package servent.handler;

import servent.message.StopShareJobMessage;

public class StopShareJobHandler implements MessageHandler {
    private StopShareJobMessage clientMessage;

    public StopShareJobHandler(StopShareJobMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {

    }
}
