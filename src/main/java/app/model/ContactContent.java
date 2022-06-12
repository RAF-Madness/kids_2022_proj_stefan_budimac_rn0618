package app.model;

import java.io.Serial;
import java.io.Serializable;

public class ContactContent implements Serializable {
    @Serial
    private static final long serialVersionUID = 900909443421456L;

    private Boolean first;
    private NodeInfoId firstNodeInfo;

    public ContactContent(Boolean first, NodeInfoId firstNodeInfo) {
        this.first = first;
        this.firstNodeInfo = firstNodeInfo;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public NodeInfoId getFirstNodeInfo() {
        return firstNodeInfo;
    }

    public void setFirstNodeInfo(NodeInfoId firstNodeInfo) {
        this.firstNodeInfo = firstNodeInfo;
    }
}
