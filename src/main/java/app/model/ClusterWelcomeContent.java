package app.model;

import java.io.Serial;
import java.util.Map;

public class ClusterWelcomeContent {
    @Serial
    private static final long serialVersionUID = 325236435233424L;

    private String fractalId;
    private Map<Integer, NodeInfo> clusterNodes;

    public ClusterWelcomeContent() {}

    public ClusterWelcomeContent(String fractalId, Map<Integer, NodeInfo> clusterNodes) {
        this.fractalId = fractalId;
        this.clusterNodes = clusterNodes;
    }

    public String getFractalId() {
        return fractalId;
    }

    public void setFractalId(String fractalId) {
        this.fractalId = fractalId;
    }

    public Map<Integer, NodeInfo> getClusterNodes() {
        return clusterNodes;
    }

    public void setClusterNodes(Map<Integer, NodeInfo> clusterNodes) {
        this.clusterNodes = clusterNodes;
    }
}
