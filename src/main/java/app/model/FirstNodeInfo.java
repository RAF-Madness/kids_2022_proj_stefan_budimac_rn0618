package app.model;

import java.io.Serial;
import java.io.Serializable;

public class FirstNodeInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 900909443421456L;

    private Boolean first;
    private NodeInfo nodeInfo;

    public FirstNodeInfo(Boolean first, NodeInfo nodeInfo) {
        this.first = first;
        this.nodeInfo = nodeInfo;
    }

    public Boolean getFirst() {
        return first;
    }

    public NodeInfo getNodeInfo() {
        return nodeInfo;
    }
}
