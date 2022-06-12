package app.model;


import java.io.Serializable;

public class BootstrapInfo implements Serializable {
    private Integer port;
    private transient String ipAddress;

    public BootstrapInfo() {}

    public void setPort(int port) {
        this.port = port;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public BootstrapInfo(int port, String ipAddress) {
        this.port = port;
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public String getIpAddress() {
        return ipAddress;
    }


}
