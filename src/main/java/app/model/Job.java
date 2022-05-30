package app.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Job implements Serializable {
    @Serial
    private static final long serialVersionUID = 4301150732952726574L;

    private String name;
    private Integer pointCount;
    private Double p;
    private Integer width;
    private Integer height;
    private List<Point> mainPoints;

    public Job() {}

    public Job(String name, Integer pointCount, Double p, Integer width, Integer height, List<Point> mainPoints) {
        this.name = name;
        this.pointCount = pointCount;
        this.p = p;
        this.width = width;
        this.height = height;
        this.mainPoints = mainPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPointCount() {
        return pointCount;
    }

    public void setPointCount(Integer pointCount) {
        this.pointCount = pointCount;
    }

    public Double getP() {
        return p;
    }

    public void setP(Double p) {
        this.p = p;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public List<Point> getMainPoints() {
        return mainPoints;
    }

    public void setMainPoints(List<Point> mainPoints) {
        this.mainPoints = mainPoints;
    }
}
