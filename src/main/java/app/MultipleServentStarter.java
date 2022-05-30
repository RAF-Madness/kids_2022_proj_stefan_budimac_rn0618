package app;

import java.util.ArrayList;
import java.util.List;

public class MultipleServentStarter {
    public static void main(String[] args) {
        List<Process> serventProcesses = new ArrayList<>();

        AppConfig.readConfig();

        AppConfig.timestampedStandardPrint("Starting multiple servent runner. If servents do not finish on their own, type \"stop\" to finish them.");
    }
}
