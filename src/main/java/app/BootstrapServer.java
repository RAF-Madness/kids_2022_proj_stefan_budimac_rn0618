package app;

import com.fasterxml.jackson.databind.ObjectMapper;

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

    private static class BootstrapInfo {
        private final int port;
        private final String ipAddress;

        private BootstrapInfo(int port, String ipAddress) {
            this.port = port;
            this.ipAddress = ipAddress;
        }
    }

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
        while (working) {
            try {
                Socket newWorkerSocket = listenerScoket.accept();
                Scanner socketScanner = new Scanner(newWorkerSocket.getInputStream());
                String message = socketScanner.nextLine();
                if (message.equals("Hail")) {
                    int newWorkerPort = socketScanner.nextInt();
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
                } else if (message.equals("New")) {
                    int newWorkerPort = socketScanner.nextInt();
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
        BootstrapInfo info = null;
        try {
            String json = new String(Files.readAllBytes(Paths.get("src/main/resources/chaos/chaos_config.json")));
            ObjectMapper mapper = new ObjectMapper();
            info = mapper.readValue(json, BootstrapInfo.class);
        } catch (IOException e) {
            AppConfig.timestampedErrorPrint("Error trying to collect bootstrap information. Exiting...");
            e.printStackTrace();
            System.exit(0);
        }
        AppConfig.timestampedStandardPrint("Bootstrap server started on port: " + info.port);
        BootstrapServer bootstrapServer = new BootstrapServer();
        bootstrapServer.bootstrap(info.port);
    }
}
