package app.model;

import java.io.Serial;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class Worker implements Serializable {
    @Serial
    private static final long serialVersionUID = 1204140642751721518L;

    private Integer port;
    private String bootstrapIpAddress;
    private Integer bootstrapPort;
    private Integer weakLimit;
    private Integer strongLimit;
    private List<Job> jobs;
    private transient Integer nodeId;
    private transient String fractalId;
    private transient NodeInfo nodeInfo;

    public Worker() {}

    public Worker(int port, String bootstrapIpAddress, Integer bootstrapPort, int weakLimit, int strongLimit, List<Job> jobs) {
        this.port = port;
        this.bootstrapIpAddress = bootstrapIpAddress;
        this.bootstrapPort = bootstrapPort;
        this.weakLimit = weakLimit;
        this.strongLimit = strongLimit;
        this.jobs = jobs;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getBootstrapIpAddress() {
        return bootstrapIpAddress;
    }

    public void setBootstrapIpAddress(String bootstrapIpAddress) {
        this.bootstrapIpAddress = bootstrapIpAddress;
    }

    public Integer getBootstrapPort() {
        return bootstrapPort;
    }

    public void setBootstrapPort(Integer bootstrapPort) {
        this.bootstrapPort = bootstrapPort;
    }

    public Integer getWeakLimit() {
        return weakLimit;
    }

    public void setWeakLimit(Integer weakLimit) {
        this.weakLimit = weakLimit;
    }

    public Integer getStrongLimit() {
        return strongLimit;
    }

    public void setStrongLimit(Integer strongLimit) {
        this.strongLimit = strongLimit;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
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

    public String getFractalId() {
        return fractalId;
    }

    public void setFractalId(String fractalId) {
        this.fractalId = fractalId;
    }
}
