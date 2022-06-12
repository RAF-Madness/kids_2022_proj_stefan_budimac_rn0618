package app;

import app.model.BootstrapInfo;
import app.model.Worker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppConfig {
    public static BootstrapInfo BOOTSTRAP;
    public static Worker info;
    public static ChaosState state;
    public static int WORKER_ID = 0;
    public static final Object idLock = new Object();
    public static final Object stateLock = new Object();

    public static void timestampedStandardPrint(String message) {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();
        System.out.println(timeFormat.format(now) + " - " + message);
    }

    public static void timestampedErrorPrint(String message) {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();
        System.err.println(timeFormat.format(now) + " - " + message);
    }

    public static void readConfig(int workerNumber) {
        try {
            String json = new String(Files.readAllBytes(Paths.get("src/main/resources/chaos/workers/worker_" + workerNumber + "/config.json")));
            Gson gson = new GsonBuilder().create();
            info = gson.fromJson(json, Worker.class);
            BOOTSTRAP = new BootstrapInfo(info.getBootstrapPort(), info.getBootstrapIpAddress());
        } catch (IOException e) {
            timestampedErrorPrint("Couldn't read the config file. Exiting...");
            System.exit(0);
            e.printStackTrace();
        }
    }
}
