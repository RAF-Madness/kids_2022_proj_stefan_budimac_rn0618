package app;

import app.model.BootstrapInfo;
import app.model.NodeInfo;
import servent.message.ContactMessage;
import servent.message.Message;
import servent.message.MessageType;
import servent.message.RejectMessage;
import servent.message.util.MessageUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class BootstrapServer {
    private volatile boolean working = true;
    private final Map<Integer, NodeInfo> activeWorkers;
    public static BootstrapInfo bootstrapInfo;

    private class BootstrapCLI implements Runnable {
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            String line;
            while (true) {
                line = scanner.nextLine();
                if (line.equals("quit")) {
                    working = false;
                    break;
                }
            }
            scanner.close();
        }
    }

    public BootstrapServer() {
        activeWorkers = new ConcurrentHashMap<>();
    }

    public void bootstrap(int bootstrapPort, String bootstrapIpAddress) {
        Thread cliThread = new Thread(new BootstrapCLI());
        cliThread.start();

        ServerSocket listenerScoket = null;
        try {
            listenerScoket = new ServerSocket(bootstrapPort);
            listenerScoket.setSoTimeout(1000);
        } catch (IOException e) {
            AppConfig.timestampedErrorPrint("An error occured while trying to open bootstrap listener socket. Exiting...");
            System.exit(0);
        }

        Random r = new Random(System.currentTimeMillis());
        Message workerMessage = null;
        while (working) {
            try {
                Socket newWorkerSocket = listenerScoket.accept();
                workerMessage = MessageUtil.readMessage(newWorkerSocket);
                if (workerMessage.getMessageType().equals(MessageType.HAIL)) {
                    System.out.println("Got " + workerMessage.getSenderPort() + ".");
                    Message contactMessage;
                    if (activeWorkers.size() == 0) {
                        NodeInfo senderInfo = new NodeInfo(bootstrapPort, bootstrapIpAddress, -1);
                        NodeInfo receiverInfo = new NodeInfo(workerMessage.getSenderPort(), workerMessage.getSenderIpAddress(), -1);
                        contactMessage = new ContactMessage(senderInfo, receiverInfo);
                        MessageUtil.sendMessage(contactMessage);
                    } else {
                        NodeInfo senderInfo = new NodeInfo(bootstrapPort, bootstrapIpAddress, -1);
                        NodeInfo receiverInfo = new NodeInfo(workerMessage.getSenderPort(), workerMessage.getSenderIpAddress(), -1);
                        contactMessage = new ContactMessage(senderInfo, receiverInfo);
                        contactMessage.setMessageContent(getHighestIdInfo());
                        MessageUtil.sendMessage(contactMessage);
                    }
                    newWorkerSocket.close();
                } else if (workerMessage.getMessageType().equals(MessageType.JOIN)) {
                    int newWorkerPort = workerMessage.getSenderPort();
                    System.out.println("Adding " + newWorkerPort);
                    //sta staviti ovde za id??
                    activeWorkers.put(-1, new NodeInfo(workerMessage.getSenderPort(), workerMessage.getSenderIpAddress(), -1));
                    newWorkerSocket.close();
                } else if (workerMessage.getMessageType().equals(MessageType.LEAVE)) {

                }
            } catch (IOException e) {
                NodeInfo senderInfo = new NodeInfo(bootstrapPort, bootstrapIpAddress, -1);
                assert workerMessage != null;
                NodeInfo receiverInfo = new NodeInfo(workerMessage.getSenderPort(), workerMessage.getSenderIpAddress(), -1);
                Message rejectMessage = new RejectMessage(senderInfo, receiverInfo);
                MessageUtil.sendMessage(rejectMessage);
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        AppConfig.timestampedStandardPrint("Bootstrap server started on port: " + AppConfig.BOOTSTRAP.getPort());
        BootstrapServer bootstrapServer = new BootstrapServer();
        bootstrapServer.bootstrap(bootstrapInfo.getPort(), bootstrapInfo.getIpAddress());
    }

    private NodeInfo getHighestIdInfo() {
        int highestId = -1;
        for (Map.Entry<Integer, NodeInfo> entry : activeWorkers.entrySet()) {
            if (entry.getKey() > highestId) {
                highestId = entry.getKey();
            }
        }
        return activeWorkers.get(highestId);
    }
}
