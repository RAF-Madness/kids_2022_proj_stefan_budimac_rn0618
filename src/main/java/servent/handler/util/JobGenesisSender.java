package servent.handler.util;

import app.AppConfig;
import app.model.GenesisContent;
import app.model.Job;
import app.model.NodeInfo;
import servent.message.ApproachClusterMessage;
import servent.message.StartJobGenesisMessage;
import servent.message.util.MessageUtil;

import java.util.*;

public class JobGenesisSender implements Runnable {
    @Override
    public void run() {
        int i;
        int n = AppConfig.state.getActiveJobs().size();
        List<Job> activeJobList = new ArrayList<>();
        for (Map.Entry<String, Job> entry : AppConfig.state.getActiveJobs().entrySet()) {
            activeJobList.add(entry.getValue());
        }
        NodeInfo[] clusterContacts = new NodeInfo[n];
        SortedMap<Integer, NodeInfo> nodeIds = new TreeMap<>(AppConfig.state.getNodes());
        for (i = 0; i < n; i++) {
            NodeInfo receiver = nodeIds.get(i);
            clusterContacts[i] = receiver;
            StartJobGenesisMessage startJobGenesisMessage = new StartJobGenesisMessage(AppConfig.info.getNodeInfo(), receiver);
            Set<String> resultSet = AppConfig.state.getResults().keySet();
            List<String> resultList = new ArrayList<>(resultSet);
            startJobGenesisMessage.setPayload(new GenesisContent(AppConfig.state.getResults().get(resultList.get(i)), activeJobList.get(i).getName()));
            MessageUtil.sendMessage(startJobGenesisMessage);
        }
        for (; i < AppConfig.state.getNodes().size(); i++) {
            NodeInfo contact = clusterContacts[i % n];
            ApproachClusterMessage approachClusterMessage = new ApproachClusterMessage(AppConfig.info.getNodeInfo(), nodeIds.get(i));
            approachClusterMessage.setPayload(contact);
            MessageUtil.sendMessage(approachClusterMessage);
        }
    }
}
