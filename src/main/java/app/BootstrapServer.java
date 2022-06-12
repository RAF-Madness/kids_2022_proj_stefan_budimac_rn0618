package app;

import app.model.BootstrapInfo;
import app.model.FirstNodeInfo;
import app.model.NodeInfo;
import app.model.NodeInfoId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import servent.message.*;
import servent.message.util.MessageUtil;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
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
        NodeInfo receiverInfo = null;
        while (working) {
            try {
                Socket newWorkerSocket = listenerScoket.accept();
                workerMessage = MessageUtil.readMessage(newWorkerSocket);
                if (workerMessage instanceof HailMessage) {
                    NodeInfo workerInfo = (NodeInfo) workerMessage.getMessageContent();
                    System.out.println("Got " + workerInfo.getPort() + ".");
                    Message contactMessage;
                    receiverInfo = new NodeInfo(workerInfo.getPort(), workerInfo.getIpAddress());
                    if (activeWorkers.size() == 0) {
                        contactMessage = new ContactMessage(-1, -2);
                        FirstNodeInfo nodeInfo = new FirstNodeInfo(Boolean.TRUE, receiverInfo);
                        contactMessage.setMessageContent(nodeInfo);
                        MessageUtil.sendMessage(contactMessage, receiverInfo);
                    } else {
                        contactMessage = new ContactMessage(-1, -2);
                        NodeInfoId infoId = new NodeInfoId(getHighestId(), workerInfo);
                        contactMessage.setMessageContent(infoId);
                        MessageUtil.sendMessage(contactMessage, receiverInfo);
                    }
                    newWorkerSocket.close();
                } else if (workerMessage.getMessageType().equals(MessageType.JOIN)) {
                    NodeInfo joinedNodeInfo = (NodeInfo) workerMessage.getMessageContent();
                    activeWorkers.put(workerMessage.getSenderId(), joinedNodeInfo);
                } else if (workerMessage.getMessageType().equals(MessageType.LEAVE)) {

                }
            } catch (SocketTimeoutException ignored) {

            } catch (IOException e) {
                e.printStackTrace();
                //assert workerMessage != null;
                //Message rejectMessage = new RejectMessage(-1, -2);
                //MessageUtil.sendMessage(rejectMessage, receiverInfo);
            }
        }
    }

    public static void main(String[] args) {
        String json;
        try {
            json = new String(Files.readAllBytes(Paths.get("src/main/resources/chaos/bootstrap_config.json")));
            Gson gson = new GsonBuilder().create();
            bootstrapInfo = gson.fromJson(json, BootstrapInfo.class);
            bootstrapInfo.setIpAddress(InetAddress.getLocalHost().getHostAddress());
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

    private Integer getHighestId() {
        int highestId = -1;
        for (Map.Entry<Integer, NodeInfo> entry : activeWorkers.entrySet()) {
            if (entry.getKey() > highestId) {
                highestId = entry.getKey();
            }
        }
        return highestId;
    }
}
