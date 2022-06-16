package app.model;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cluster implements Serializable {
    private Map<Integer, NodeInfo> clusterNodes;

    public Cluster() {
        clusterNodes = new ConcurrentHashMap<>();
    }

    public Map<Integer, NodeInfo> getClusterNodes() {
        return clusterNodes;
    }

    public void setClusterNodes(Map<Integer, NodeInfo> clusterNodes) {
        this.clusterNodes = clusterNodes;
    }
}
