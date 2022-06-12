package app.model;

import app.ChaosState;

import java.io.Serial;
import java.io.Serializable;

public class WelcomeContent implements Serializable {
    @Serial
    private static final long serialVersionUID = 700707443421456L;

    private Integer newId;
    private ChaosState state;

    public WelcomeContent(Integer newId, ChaosState state) {
        this.newId = newId;
        this.state = state;
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
}
