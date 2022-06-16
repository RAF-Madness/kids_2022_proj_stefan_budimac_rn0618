package cli.command;

import app.AppConfig;
import app.model.Job;
import app.model.NodeInfo;
import app.model.Point;
import servent.message.StopShareJobMessage;
import servent.message.util.MessageUtil;

import java.util.*;

public class StartCommand implements CLICommand {
    @Override
    public String commandName() {
        return "start";
    }

    @Override
    public void execute(String args) {
        if (args != null) {
            Job job = AppConfig.state.getJobs().get(args);
            if (job == null) {
                AppConfig.timestampedErrorPrint("No such job with given name.");
                return;
            }
            if (AppConfig.state.getActiveJobs().containsKey(job.getName())) {
                AppConfig.timestampedErrorPrint("Job is already being worked on.");
                return;
            }
            if (AppConfig.state.getActiveJobs().size() >= AppConfig.state.getNodes().size()) {
                AppConfig.timestampedErrorPrint("Not enough workers to do the job.");
                return;
            }
            AppConfig.state.getActiveJobs().put(job.getName(), job);
            broadcastStopShareJobs(job.getName());
        } else {
            if (AppConfig.state.getActiveJobs().size() >= AppConfig.state.getNodes().size()) {
                AppConfig.timestampedErrorPrint("Not enough workers to do the job.");
                return;
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter job name: ");
            String name = scanner.nextLine();
            if (AppConfig.state.getJobs().containsKey(name)) {
                AppConfig.timestampedErrorPrint("Job with given name already exists.");
                return;
            }
            try {
                System.out.println("Enter job point count: ");
                Integer pointCount = scanner.nextInt();
                System.out.println("Enter point generation proportion: ");
                Double p = scanner.nextDouble();
                System.out.println("Enter pane width: ");
                Integer width = scanner.nextInt();
                System.out.println("Enter pane height: ");
                Integer height = scanner.nextInt();
                System.out.println("Enter main points of the fractal structure: ");
                String points = scanner.nextLine();
                List<Point> mainPoints = new ArrayList<>();
                String[] pointList = points.split(",");
                for (int i = 0; i < pointList.length; i += 2) {
                    mainPoints.add(new Point(Double.parseDouble(pointList[i]), Double.parseDouble(pointList[i + 1])));
                }
                Job newJob = new Job(name, pointCount, p, width, height, mainPoints);
                if (AppConfig.state.getActiveJobs().containsKey(newJob.getName())) {
                    AppConfig.timestampedErrorPrint("Job is already being worked on.");
                    return;
                }
                AppConfig.state.getActiveJobs().put(name, newJob);
                broadcastStopShareJobs(name);
            } catch (InputMismatchException | NumberFormatException e) {
                AppConfig.timestampedErrorPrint("Invalid job info entered.");
                return;
            }
        }
    }

    private void broadcastStopShareJobs(String payload) {
        for (Map.Entry<Integer, NodeInfo> entry : AppConfig.state.getNodes().entrySet()) {
            if (entry.getKey().equals(AppConfig.info.getNodeId())) {
                continue;
            }
            StopShareJobMessage stopShareJobMessage = new StopShareJobMessage(AppConfig.info.getNodeInfo(), entry.getValue());
            stopShareJobMessage.setPayload(payload);
            MessageUtil.sendMessage(stopShareJobMessage);
        }
    }
}
