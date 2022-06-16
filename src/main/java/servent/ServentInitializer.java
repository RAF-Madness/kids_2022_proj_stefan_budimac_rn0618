package servent;

import app.AppConfig;
import app.model.NodeInfo;
import servent.message.HailMessage;
import servent.message.Message;
import servent.message.util.MessageUtil;

import java.io.IOException;
import java.net.InetAddress;

public class ServentInitializer implements Runnable {

    @Override
    public void run() {
        try {
            NodeInfo senderInfo = new NodeInfo(AppConfig.info.getPort(), InetAddress.getLocalHost().getHostAddress(), -2, "", "");
            AppConfig.info.setNodeInfo(senderInfo);
            NodeInfo receiverInfo = new NodeInfo(AppConfig.info.getBootstrapPort(), AppConfig.info.getBootstrapIpAddress(), -1, "", "");
            HailMessage hailMessage = new HailMessage(senderInfo, receiverInfo);
            MessageUtil.sendMessage(hailMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
