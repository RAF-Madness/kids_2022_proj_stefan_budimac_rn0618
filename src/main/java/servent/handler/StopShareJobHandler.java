package servent.handler;

import app.AppConfig;
import servent.message.StopShareJobMessage;
import servent.message.StoppedJobInfoMessage;
import servent.message.util.MessageUtil;

public class StopShareJobHandler implements MessageHandler {
    private StopShareJobMessage clientMessage;

    public StopShareJobHandler(StopShareJobMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {
        //if (AppConfig.info.getFractalCruncher().isWorking()) {
        //    AppConfig.info.getFractalCruncher().stop();
        //}
        StoppedJobInfoMessage stoppedJobInfoMessage = new StoppedJobInfoMessage(AppConfig.info.getNodeInfo(), clientMessage.getSender());
        AppConfig.info.setCurrentJobName(clientMessage.getPayload());
        stoppedJobInfoMessage.setPayload(AppConfig.state.getResults().get(AppConfig.info.getCurrentJobName()));
        MessageUtil.sendMessage(stoppedJobInfoMessage);
    }
}
