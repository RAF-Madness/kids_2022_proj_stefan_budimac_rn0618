package app.model;

import app.FractalCruncher;

import java.io.Serial;
import java.io.Serializable;
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
    private transient NodeInfo previous;
    private transient NodeInfo next;
    private transient FractalCruncher fractalCruncher;
    private transient JobStatus jobStatus;
    private transient String currentJobName;

    public Worker() {
        this.jobStatus = new JobStatus();
    }

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

    public String getCurrentJobName() {
        return currentJobName;
    }

    public void setCurrentJobName(String currentJobName) {
        this.currentJobName = currentJobName;
    }

    public FractalCruncher getFractalCruncher() {
        return fractalCruncher;
    }

    public void setFractalCruncher(FractalCruncher fractalCruncher) {
        this.fractalCruncher = fractalCruncher;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }
}
