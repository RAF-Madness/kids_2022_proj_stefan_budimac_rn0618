package app;

import app.model.*;
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
    public static final Object messageLock = new Object();

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
            synchronized (messageLock) {
                try {
                    Socket newWorkerSocket = listenerScoket.accept();
                    workerMessage = MessageUtil.readMessage(newWorkerSocket);
                    if (workerMessage instanceof HailMessage) {
                        NodeInfo workerInfo = workerMessage.getSender();
                        System.out.println("Got " + workerInfo.getPort() + ".");
                        Message contactMessage;
                        if (activeWorkers.size() == 0) {
                            contactMessage = new ContactMessage(new NodeInfo(bootstrapPort, bootstrapIpAddress, -2, "", ""), workerMessage.getSender());
                            contactMessage.setPayload(new ContactContent(Boolean.TRUE, new NodeInfoId(-1, new NodeInfo())));
                            MessageUtil.sendMessage(contactMessage);
                        } else {
                            contactMessage = new ContactMessage(new NodeInfo(bootstrapPort, bootstrapIpAddress, -2, "", ""), workerMessage.getSender());
                            contactMessage.setPayload(new ContactContent(Boolean.FALSE, new NodeInfoId(getHighestId(), getHighestIdInfo())));
                            MessageUtil.sendMessage(contactMessage);
                        }
                        newWorkerSocket.close();
                    } else if (workerMessage instanceof JoinMessage) {
                        Integer joinedNodeId = (Integer) workerMessage.getPayload();
                        activeWorkers.put(joinedNodeId, workerMessage.getSender());
                        System.out.println("Worker successfully added to the system!");
                    } else if (workerMessage.getType().equals(MessageType.LEAVE)) {

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
