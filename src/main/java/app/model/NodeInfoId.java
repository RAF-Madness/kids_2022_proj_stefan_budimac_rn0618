package app.model;

import java.io.Serial;
import java.io.Serializable;

public class NodeInfoId implements Serializable {
    @Serial
    private static final long serialVersionUID = 3748392837213834L;

    private Integer nodeId;
    private NodeInfo nodeInfo;

    public NodeInfoId(Integer nodeId, NodeInfo nodeInfo) {
        this.nodeId = nodeId;
        this.nodeInfo = nodeInfo;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public NodeInfo getNodeInfo() {
        return nodeInfo;
    }

    public void setNodeInfo(NodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
    }
}
