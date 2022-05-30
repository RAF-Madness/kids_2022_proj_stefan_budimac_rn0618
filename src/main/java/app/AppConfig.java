package app;

import app.model.Worker;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AppConfig {
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

    public static void readConfig() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("src/main/resources/chaos/chaos_config.json")));
            ObjectMapper mapper = new ObjectMapper();
            Worker[] workers = mapper.readValue(json, Worker[].class);
            System.out.println(workers.length);
        } catch (IOException e) {
            timestampedErrorPrint("Couldn't read the config file. Exiting...");
            System.exit(0);
            e.printStackTrace();
        }
    }
}
