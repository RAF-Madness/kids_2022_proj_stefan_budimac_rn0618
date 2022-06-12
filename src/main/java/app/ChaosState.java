package app;

import app.model.NodeInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChaosState {
    private Map<Integer, NodeInfo> nodes;
    private NodeInfo previous;
    private NodeInfo next;

    public ChaosState() {
        nodes = new ConcurrentHashMap<>();
    }

    public NodeInfo getPrevious() {
        return previous;
    }

    public void setPrevious(NodeInfo preveous) {
        this.previous = preveous;
    }

    public NodeInfo getNext() {
        return next;
    }

    public void setNext(NodeInfo next) {
        this.next = next;
    }

    public Map<Integer, NodeInfo> getNodes() {
        return nodes;
    }

    public void setNodes(Map<Integer, NodeInfo> nodes) {
        this.nodes = nodes;
    }

    public NodeInfo getFirstNode() {
        for (Map.Entry<Integer, NodeInfo> entry : nodes.entrySet()) {
            if (entry.getKey() == 0) {
                return entry.getValue();
            }
        }
        return null;
    }
}
