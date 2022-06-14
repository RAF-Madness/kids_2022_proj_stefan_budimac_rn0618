package app.model;

import java.io.Serial;
import java.io.Serializable;

public class NodeInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 22225632171121456L;

    private Integer port;
    private String ipAddress;
    private Integer nodeId;
    private String fractalId;
    private String jobName;

    public NodeInfo() {}

    public NodeInfo(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public NodeInfo(Integer port, String ipAddress, Integer nodeId, String fractalId, String jobName) {
        this.port = port;
        this.ipAddress = ipAddress;
        this.nodeId = nodeId;
        this.fractalId = fractalId;
        this.jobName = jobName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getFractalId() {
        return fractalId;
    }

    public void setFractalId(String fractalId) {
        this.fractalId = fractalId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
