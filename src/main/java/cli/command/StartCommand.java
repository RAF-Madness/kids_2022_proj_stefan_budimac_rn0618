package cli.command;

import app.AppConfig;
import app.model.Job;
import app.model.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            if (AppConfig.state.getJobs().containsKey(job.getName())) {
                AppConfig.timestampedErrorPrint("Job with given name already exists.");
                return;
            }
            if (AppConfig.state.activeJobs().containsKey(job.getName())) {
                AppConfig.timestampedErrorPrint("Job is already being worked on.");
                return;
            }
            if (AppConfig.state.activeJobs().size() >= AppConfig.state.getNodes().size()) {
                AppConfig.timestampedErrorPrint("Not enough workers to do the job.");
                return;
            }
        } else {
            if (AppConfig.state.activeJobs().size() >= AppConfig.state.getNodes().size()) {
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
            if (AppConfig.state.activeJobs().containsKey(newJob.getName())) {
                AppConfig.timestampedErrorPrint("Job is already being worked on.");
                return;
            }
        }
    }
}
