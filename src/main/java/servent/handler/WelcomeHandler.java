package servent.handler;

import app.AppConfig;
import app.model.NodeInfo;
import servent.message.Message;

public class WelcomeHandler implements MessageHandler {
    private Message clientMessage;

    public WelcomeHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        NodeInfo content = (NodeInfo) clientMessage.getMessageContent();
        AppConfig.info.setWorkerId(content.getId());
        //TODO poslati tabelu informacija o ostatku sistema
    }
}
