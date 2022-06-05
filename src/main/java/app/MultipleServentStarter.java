package app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultipleServentStarter {
    private static final int SERVENT_COUNT = 3;
    private static class ServentCLI implements Runnable {
        private final List<Process> serventProcesses;
        private final Process bootstrapProcess;

        public ServentCLI(Process bootstrapProcess, List<Process> serventProcesses) {
            this.bootstrapProcess = bootstrapProcess;
            this.serventProcesses = serventProcesses;
        }

        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String line = scanner.nextLine();
                if (line.equals("stop")) {
                    for (Process process : serventProcesses) {
                        process.destroy();
                    }
                    bootstrapProcess.destroy();
                    break;
                }
            }
            scanner.close();
        }
    }

    public static void main(String[] args) {
        Process bootstrapProcess = null;
        List<Process> serventProcesses = new ArrayList<>();
        AppConfig.readConfig(0);
        AppConfig.timestampedStandardPrint("Starting multiple servent runner. If servents do not finish on their own, type \"stop\" to finish them.");
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", "target\\classes\\app.BootstrapServer");
        try {
            bootstrapProcess = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < SERVENT_COUNT; i++) {
            try {
                processBuilder = new ProcessBuilder("java", "-cp", "target\\classes\\app.ServentMain", String.valueOf(i));
                processBuilder.redirectInput(new File("src/main/resources/chaos/input/worker" + i + "_in.txt"));
                processBuilder.redirectOutput(new File("src/main/resources/chaos/output/worker" + i + "_out.txt"));
                processBuilder.redirectError(new File("src/main/resources/chaos/error/worker" + i + "_err.txt"));

                Process worker = processBuilder.start();
                serventProcesses.add(worker);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread cliThread = new Thread(new ServentCLI(bootstrapProcess, serventProcesses));
        cliThread.start();
        for (Process process : serventProcesses) {
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        AppConfig.timestampedStandardPrint("All servent processes finished. Type \"stop\" to halt bootstrap.");
        try {
            assert bootstrapProcess != null;
            bootstrapProcess.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
