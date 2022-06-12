package servent;

import app.AppConfig;
import app.model.NodeInfo;
import servent.message.HailMessage;
import servent.message.JoinMessage;
import servent.message.Message;
import servent.message.util.MessageUtil;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServentInitializer implements Runnable {

    @Override
    public void run() {
        try {
            NodeInfo senderInfo = new NodeInfo(AppConfig.info.getPort(), InetAddress.getLocalHost().getHostAddress());
            NodeInfo receiverInfo = new NodeInfo(AppConfig.info.getBootstrapPort(), AppConfig.info.getBootstrapIpAddress());
            Message hailMessage = new HailMessage(senderInfo, receiverInfo);
            MessageUtil.sendMessage(hailMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        if (serventPort == 2) {
            AppConfig.timestampedErrorPrint("Error while contacting bootstrap. Exiting...");
            System.exit(0);
        }
        if (serventPort == 1) {
            AppConfig.timestampedStandardPrint("First node to enter the chaos, welcome!");
        } else {
            try {
                NodeInfo senderInfo = new NodeInfo(AppConfig.info.getPort(),  InetAddress.getLocalHost().getHostAddress(), -1);
                NodeInfo receiverInfo = new NodeInfo(serventPort, "localhost???", -1);
                Message joinMessage = new JoinMessage(senderInfo, receiverInfo);
                MessageUtil.sendMessage(joinMessage);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        */
    }
}
