package app;

import app.model.Job;
import app.model.NodeInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChaosState {
    private Map<Integer, NodeInfo> nodes;
    private NodeInfo previous;
    private NodeInfo next;
    private Map<String, Job> jobs;

    public ChaosState() {
        nodes = new ConcurrentHashMap<>();
        jobs = new ConcurrentHashMap<>();
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

    public Map<Integer, NodeInfo> getNodes() {
        return nodes;
    }

    public void setNodes(Map<Integer, NodeInfo> nodes) {
        this.nodes = nodes;
    }

    public Map<String, Job> getJobs() {
        return jobs;
    }

    public void setJobs(Map<String, Job> jobs) {
        this.jobs = jobs;
    }

    public NodeInfo getFirstNode() {
        for (Map.Entry<Integer, NodeInfo> entry : nodes.entrySet()) {
            if (entry.getKey() == 0) {
                return entry.getValue();
            }
        }
        return null;
    }

    public Map<String, Job> activeJobs() {
        Map<String, Job> activeJobs = new HashMap<>();
        for (Map.Entry<Integer, NodeInfo> entry : nodes.entrySet()) {
            NodeInfo node = entry.getValue();
            if (!node.getJobName().equals("")) {
                activeJobs.put(jobs.get(node.getJobName()).getName(), jobs.get(node.getJobName()));
            }
        }
        return activeJobs;
    }
}
