package app.model;

public class BootstrapInfo {
    private final int port;
    private final String ipAddress;

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
