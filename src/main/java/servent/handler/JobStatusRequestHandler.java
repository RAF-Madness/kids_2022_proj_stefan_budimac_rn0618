package servent.handler;

import servent.message.JobStatusRequestMessage;

public class JobStatusRequestHandler implements MessageHandler {
    private JobStatusRequestMessage clientMessage;

    public JobStatusRequestHandler(JobStatusRequestMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {

    }
}
