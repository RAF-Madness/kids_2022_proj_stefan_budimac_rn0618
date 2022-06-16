package app;

import app.model.Job;
import app.model.JobStatus;
import app.model.NodeInfo;
import app.model.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChaosState {
    private Map<Integer, NodeInfo> nodes;
    private Map<String, Job> jobs;
    private Map<String, Job> activeJobs;
    private Map<String, JobStatus> jobStatusMap;
    private static final Object fractalLock = new Object();
    private Map<String, Result> results;
    private Map<Integer, NodeInfo> clusterNodes;
    private Map<Integer, NodeInfo> clusterNeighbours;

    public ChaosState() {
        nodes = new ConcurrentHashMap<>();
        jobs = new ConcurrentHashMap<>();
        activeJobs = new ConcurrentHashMap<>();
        jobStatusMap = new ConcurrentHashMap<>();
        results = new ConcurrentHashMap<>();
        clusterNodes = new ConcurrentHashMap<>();
        clusterNeighbours = new ConcurrentHashMap<>();
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

    public Map<String, Job> getActiveJobs() {
        return activeJobs;
    }

    public void setActiveJobs(Map<String, Job> activeJobs) {
        this.activeJobs = activeJobs;
    }

    public Map<String, JobStatus> getJobStatusMap() {
        return jobStatusMap;
    }

    public void setJobStatusMap(Map<String, JobStatus> jobStatusMap) {
        this.jobStatusMap = jobStatusMap;
    }

    public Map<String, Result> getResults() {
        return results;
    }

    public void setResults(Map<String, Result> results) {
        this.results = results;
    }

    public Map<Integer, NodeInfo> getClusterNodes() {
        return clusterNodes;
    }

    public void setClusterNodes(Map<Integer, NodeInfo> clusterNodes) {
        this.clusterNodes = clusterNodes;
    }

    public Map<Integer, NodeInfo> getClusterNeighbours() {
        return clusterNeighbours;
    }

    public void setClusterNeighbours(Map<Integer, NodeInfo> clusterNeighbours) {
        this.clusterNeighbours = clusterNeighbours;
    }

    public void mergeJobStatus(JobStatus jobStatus) {
        if (jobStatusMap.containsKey(jobStatus.getName())) {
            JobStatus merger = jobStatusMap.get(jobStatus.getName());
            merger.setPointsGenerated(merger.getPointsGenerated() + jobStatus.getPointsGenerated());
            merger.setWorkingNodes(merger.getWorkingNodes() + jobStatus.getWorkingNodes());
            for (Map.Entry<String, Integer> entry : jobStatus.getPointNodes().entrySet()) {
                merger.getPointNodes().merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
            jobStatusMap.put(jobStatus.getName(), merger);
        } else {
            jobStatusMap.put(jobStatus.getName(), jobStatus);
        }
    }

    public void mergeResults(Result result) {
        results.get(result.getJobName()).getPoints().addAll(result.getPoints());
    }

    public String generateFractalId() {
        synchronized (fractalLock) {
            int maxLen = 1;
            for (Map.Entry<Integer, NodeInfo> entry : nodes.entrySet()) {
                if (entry.getValue().getFractalId().length() > maxLen) {
                    maxLen = entry.getValue().getFractalId().length();
                }
            }
            List<String> longIds = new ArrayList<>();
            for (Map.Entry<Integer, NodeInfo> entry : nodes.entrySet()) {
                if (entry.getValue().getFractalId().length() == maxLen) {
                    longIds.add(entry.getValue().getFractalId());
                }
            }
            String newFractalId = longIds.get(0);
            for (String id : longIds) {
                if (Integer.parseInt(id.substring(id.length() - 1)) > Integer.parseInt(newFractalId.substring(newFractalId.length() - 1))) {
                    newFractalId = id;
                }
            }
            int lastDigit = Integer.parseInt(newFractalId.substring(newFractalId.length() - 1));
            if (lastDigit + 1 >= clusterNodes.size()) {
                newFractalId = "0" + newFractalId;
            } else {
                StringBuilder stringBuffer = new StringBuilder(newFractalId);
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                newFractalId += Integer.toString(lastDigit);
            }
            return newFractalId;
        }
    }

    public Map<Integer, NodeInfo> isDifferenceInOne(String fractalId) {
        Map<Integer, NodeInfo> result = new HashMap<>();
        int maxLen = 1;
        for (Map.Entry<Integer, NodeInfo> entry : clusterNodes.entrySet()) {
            if (entry.getValue().getFractalId().length() > maxLen) {
                maxLen = entry.getValue().getFractalId().length();
            }
        }
        int zeros = 0;
        for (Map.Entry<Integer, NodeInfo> entry : clusterNodes.entrySet()) {
            zeros = maxLen - entry.getValue().getFractalId().length();
        }
        fractalId = "0".repeat(Math.max(0, zeros)) + fractalId;
        for (Map.Entry<Integer, NodeInfo> entry : clusterNodes.entrySet()) {
            if (isDifferenceInOne(fractalId, entry.getValue().getFractalId())) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    public boolean isDifferenceInOne(String fractalId, String nodeId) {
        char[] fractalDigits = fractalId.toCharArray();
        char[] nodeDigits = nodeId.toCharArray();
        int diff = 0;
        for (int i = 0; i < fractalId.length(); i++) {
            if (fractalDigits[i] != nodeDigits[i]) {
                diff++;
            }
        }
        if (diff == 1) {
            return true;
        }
        return false;
    }
}
