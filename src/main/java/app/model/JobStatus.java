package app.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

public class JobStatus implements Serializable {
    @Serial
    private static final long serialVersionUID = 458390760892347564L;

    private String name;
    private Integer pointsGenerated;
    private Integer workingNodes;
    private Map<String, Integer> pointNodes;

    public JobStatus() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPointsGenerated() {
        return pointsGenerated;
    }

    public void setPointsGenerated(Integer pointsGenerated) {
        this.pointsGenerated = pointsGenerated;
    }

    public Integer getWorkingNodes() {
        return workingNodes;
    }

    public void setWorkingNodes(Integer workingNodes) {
        this.workingNodes = workingNodes;
    }

    public Map<String, Integer> getPointNodes() {
        return pointNodes;
    }

    public void setPointNodes(Map<String, Integer> pointNodes) {
        this.pointNodes = pointNodes;
    }
}
