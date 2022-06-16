package servent.handler;

import servent.message.JobStatusMessage;

public class JobStatusHandler implements MessageHandler {
    private JobStatusMessage clientMessage;

    public JobStatusHandler(JobStatusMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {

    }
}
