package servent.handler;

import app.AppConfig;
import servent.message.StartJobGenesisMessage;

public class StartJobGenesisHandler implements MessageHandler {
    public StartJobGenesisMessage clientMessage;

    public StartJobGenesisHandler(StartJobGenesisMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        AppConfig.state.getClusterNodes().put(AppConfig.info.getNodeId(), AppConfig.info.getNodeInfo());
        AppConfig.info.setFractalId("0");
        AppConfig.info.getFractalCruncher().setWorking();
        AppConfig.info.getFractalCruncher().setResult(clientMessage.getPayload().getResult());
        AppConfig.info.setCurrentJobName(clientMessage.getPayload().getJobName());
        AppConfig.info.getFractalCruncher().run();
    }
}
