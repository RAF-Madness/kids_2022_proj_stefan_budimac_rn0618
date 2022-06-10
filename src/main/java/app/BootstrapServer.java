package app;

import app.model.BootstrapInfo;
import app.model.NodeInfo;
import app.model.Worker;
import com.fasterxml.jackson.databind.ObjectMapper;
import servent.message.ContactMessage;
import servent.message.Message;
import servent.message.MessageType;
import servent.message.RejectMessage;
import servent.message.util.MessageUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
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
                        contactMessage.getMessageContent().add(Boolean.TRUE);
                        MessageUtil.sendMessage(contactMessage);
                    } else {
                        NodeInfo senderInfo = new NodeInfo(bootstrapPort, bootstrapIpAddress, -1);
                        NodeInfo receiverInfo = new NodeInfo(workerMessage.getSenderPort(), workerMessage.getSenderIpAddress(), -1);
                        contactMessage = new ContactMessage(senderInfo, receiverInfo);
                        contactMessage.getMessageContent().add(Boolean.FALSE);
                        contactMessage.getMessageContent().add(getHighestIdInfo());
                        MessageUtil.sendMessage(contactMessage);
                    }
                    newWorkerSocket.close();
                } else if (workerMessage.getMessageType().equals(MessageType.JOIN)) {
                    NodeInfo joinedNode = new NodeInfo(workerMessage.getSenderPort(),
                            workerMessage.getSenderIpAddress(),
                            workerMessage.getSenderId());
                    activeWorkers.put(workerMessage.getSenderId(), joinedNode);
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
        String json;
        try {
            json = new String(Files.readAllBytes(Paths.get("src/main/resources/chaos/bootstrap_config.json")));
            ObjectMapper mapper = new ObjectMapper();
            bootstrapInfo = mapper.readValue(json, BootstrapInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AppConfig.timestampedStandardPrint("Bootstrap server started on port: " + bootstrapInfo.getPort());
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
