package app;

import app.model.Point;
import app.model.Result;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FractalCruncher implements Runnable, Cancellable {
    private volatile boolean working = true;
    private Result result;

    public FractalCruncher( ) {

    }

    @Override
    public void run() {
        Point point;
        System.out.println(AppConfig.state.getJobs());
        System.out.println(AppConfig.info.getCurrentJobName());
        int pointCount = AppConfig.state.getJobs().get(AppConfig.info.getCurrentJobName()).getPointCount();
        Random r = new Random();
        if (AppConfig.state.getResults().size() == 0) {
            point = AppConfig.state.getJobs().get(AppConfig.info.getCurrentJobName()).getMainPoints().get(r.nextInt(pointCount));
        } else {
            point = result.getPoints().get(result.getPoints().size() - 1);
        }
        while (working) {
            point = calculate(point);
            result.getPoints().add(point);
        }
    }

    @Override
    public void stop() {
        this.working = false;
    }

    public void setWorking() {
        this.working = true;
    }

    public boolean isWorking() {
        return working;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    private Point calculate(Point point) {
        int pointCount = AppConfig.state.getJobs().get(AppConfig.info.getCurrentJobName()).getPointCount();
        int random = ThreadLocalRandom.current().nextInt(pointCount);
        Point anchorPoint = AppConfig.state.getJobs().get(AppConfig.info.getCurrentJobName()).getMainPoints().get(random);
        Double p = AppConfig.state.getJobs().get(AppConfig.info.getCurrentJobName()).getP();
        Double x = point.getX() * p + (1 - p) * anchorPoint.getX();
        Double y = point.getY() * p + (1 - p) * anchorPoint.getY();
        return new Point(x, y);
    }
}
