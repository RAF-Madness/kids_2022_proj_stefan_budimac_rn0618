package servent;

import app.AppConfig;
import app.model.NodeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import servent.message.HailMessage;
import servent.message.JoinMessage;
import servent.message.Message;
import servent.message.util.MessageUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServentInitializer implements Runnable {
    private int getServentPort() {
        int ret = -2;
        try {
            Socket bootstrapSocket = new Socket(AppConfig.info.getBootstrapIpAddress(), AppConfig.info.getBootstrapPort());
            PrintWriter bootstrapWriter = new PrintWriter(bootstrapSocket.getOutputStream());
            NodeInfo senderInfo = new NodeInfo(AppConfig.info.getPort(), InetAddress.getLocalHost().getHostAddress());
            NodeInfo receiverInfo = new NodeInfo(AppConfig.info.getBootstrapPort(), AppConfig.info.getBootstrapIpAddress());
            Message hailMessage = new HailMessage(senderInfo, receiverInfo);
            ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String toSend = objectWriter.writeValueAsString(hailMessage);
            bootstrapWriter.write(toSend);
            bootstrapWriter.flush();

            Scanner bootstrapScanner = new Scanner(bootstrapSocket.getInputStream());
            ret = bootstrapScanner.nextInt();
            bootstrapSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public void run() {
        int serventPort = getServentPort();
        if (serventPort == 2) {
            AppConfig.timestampedErrorPrint("Error while contacting bootstrap. Exiting...");
            System.exit(0);
        }
        if (serventPort == 1) {
            AppConfig.timestampedStandardPrint("First node to enter the chaos, welcome!");
        } else {
            try {
                NodeInfo senderInfo = new NodeInfo(AppConfig.info.getPort(),  InetAddress.getLocalHost().getHostAddress());
                NodeInfo receiverInfo = new NodeInfo(serventPort, "localhost???");
                Message joinMessage = new JoinMessage(senderInfo, receiverInfo);
                MessageUtil.sendMessage(joinMessage);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }
}
