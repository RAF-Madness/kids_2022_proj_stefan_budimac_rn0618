package servent.handler;

import app.AppConfig;
import app.model.KnockAnswer;
import app.model.NodeInfo;
import app.model.NodeInfoId;
import servent.message.ConnectionRequestMessage;
import servent.message.Message;
import servent.message.util.MessageUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class WelcomeHandler implements MessageHandler {
    private Message clientMessage;

    public WelcomeHandler(Message clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        KnockAnswer knockAnswer = (KnockAnswer) clientMessage.getMessageContent();
        AppConfig.info.setWorkerId(knockAnswer.getNewId());
        AppConfig.state = knockAnswer.getState();
        synchronized (AppConfig.stateLock) {
            AppConfig.state.setPrevious(knockAnswer.getPredecessorInfo());
        }
        Message connectionRequestMessage = new ConnectionRequestMessage(AppConfig.info.getWorkerId(), 0);
        try {
            NodeInfo newNodeInfo = new NodeInfo(AppConfig.info.getPort(), InetAddress.getLocalHost().getHostAddress());
            connectionRequestMessage.setMessageContent(newNodeInfo);
            MessageUtil.sendMessage(connectionRequestMessage, AppConfig.state.getFirstNode());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
