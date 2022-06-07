package app;

import app.model.BootstrapInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import servent.message.Message;
import servent.message.MessageType;
import servent.message.util.MessageUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BootstrapServer {
    private volatile boolean working = true;
    private final List<Integer> activeWorkerIds;
    public static BootstrapInfo info;

    private class BootstrapCLI implements Runnable {
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            String line;
            while (true) {
                line = scanner.nextLine();
                if (line.equals("stop")) {
                    working = false;
                    break;
                }
            }
            scanner.close();
        }
    }

    public BootstrapServer() {
        activeWorkerIds = new ArrayList<>();
    }

    public void bootstrap(int bootstrapPort) {
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
        Message workerMessage;
        while (working) {
            try {
                Socket newWorkerSocket = listenerScoket.accept();
                workerMessage = MessageUtil.readMessage(newWorkerSocket);
                if (workerMessage.getMessageType().equals(MessageType.HAIL)) {
                    int newWorkerPort = workerMessage.getSenderPort();
                    System.out.println("Got " + newWorkerPort + ".");
                    PrintWriter socketWriter = new PrintWriter(newWorkerSocket.getOutputStream());
                    if (activeWorkerIds.size() == 0) {
                        socketWriter.write((-1) + "\n");
                        activeWorkerIds.add(newWorkerPort);
                    } else {
                        int randomWorker = activeWorkerIds.get(r.nextInt(activeWorkerIds.size()));
                        socketWriter.write((randomWorker) + "\n");
                    }
                    socketWriter.flush();
                    newWorkerSocket.close();
                } else if (workerMessage.getMessageType().equals(MessageType.NEW_NODE)) {
                    int newWorkerPort = workerMessage.getSenderPort();
                    System.out.println("Adding " + newWorkerPort);
                    activeWorkerIds.add(newWorkerPort);
                    newWorkerSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        AppConfig.timestampedStandardPrint("Bootstrap server started on port: " + AppConfig.BOOTSTRAP.getPort());
        BootstrapServer bootstrapServer = new BootstrapServer();
        bootstrapServer.bootstrap(info.getPort());
    }
}
