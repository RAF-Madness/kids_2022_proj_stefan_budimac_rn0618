package servent.handler;

import app.AppConfig;
import app.model.NodeInfo;
import servent.message.Message;

public class EnterHandler implements MessageHandler {
    private Message clientMessage;

    public EnterHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        NodeInfo enteredNode = new NodeInfo(clientMessage.getSenderPort(),
                                            clientMessage.getSenderIpAddress(),
                                            (Integer) clientMessage.getMessageContent().get(0));
        synchronized (AppConfig.stateLock) {
            AppConfig.state.getNodes().put((Integer) clientMessage.getMessageContent().get(0), enteredNode);
        }
    }
}
