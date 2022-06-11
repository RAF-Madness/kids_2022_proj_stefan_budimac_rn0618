package app.model;

import java.io.Serial;
import java.io.Serializable;

public class NodeInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 22225632171121456L;

    private Integer port;
    private String ipAddress;

    public NodeInfo(Integer port, String ipAddress) {
        this.port = port;
        this.ipAddress = ipAddress;
    }

    public Integer getPort() {
        return port;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
