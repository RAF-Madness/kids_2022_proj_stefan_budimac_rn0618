package app.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Worker implements Serializable {
    @Serial
    private static final long serialVersionUID = 1204140642751721518L;

    private Integer port;
    private String ipAddress;
    private Integer weakLimit;
    private Integer strongLimit;
    private List<Job> jobs;

    public Worker() {}

    public Worker(int port, String ipAddress, int weakLimit, int strongLimit, List<Job> jobs) {
        this.port = port;
        this.ipAddress = ipAddress;
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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
}
