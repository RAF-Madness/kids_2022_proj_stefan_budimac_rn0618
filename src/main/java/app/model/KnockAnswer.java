package app.model;

import app.ChaosState;

import java.io.Serial;
import java.io.Serializable;

public class KnockAnswer implements Serializable {
    @Serial
    private static final long serialVersionUID = 700707443421456L;

    private Integer newId;
    private ChaosState state;
    private NodeInfo predecessorInfo;

    public KnockAnswer(Integer newId, ChaosState state, NodeInfo predecessorInfo) {
        this.newId = newId;
        this.state = state;
        this.predecessorInfo = predecessorInfo;
    }

    public Integer getNewId() {
        return newId;
    }

    public void setNewId(Integer newId) {
        this.newId = newId;
    }

    public ChaosState getState() {
        return state;
    }

    public void setState(ChaosState state) {
        this.state = state;
    }

    public NodeInfo getPredecessorInfo() {
        return predecessorInfo;
    }

    public void setPredecessorInfo(NodeInfo predecessorInfo) {
        this.predecessorInfo = predecessorInfo;
    }
}
