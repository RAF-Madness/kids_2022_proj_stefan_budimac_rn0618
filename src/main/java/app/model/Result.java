package app.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Result implements Serializable {
    @Serial
    private static final long serialVersionUID = 34872347293576L;

    private String jobName;
    private List<Point> points;

    public Result() {
        points = new ArrayList<>();
    }

    public Result(String jobName) {
        this.jobName = jobName;
        points = new ArrayList<>();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
