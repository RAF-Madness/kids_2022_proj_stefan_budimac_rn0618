package servent.handler;

import servent.message.StoppedJobInfoMessage;

public class StoppedJobInfoHandler implements MessageHandler {
    private StoppedJobInfoMessage clientMessage;

    public StoppedJobInfoHandler(StoppedJobInfoMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {

    }
}
