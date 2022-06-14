package cli.command;

import app.AppConfig;
import app.model.NodeInfo;
import cli.CLIParser;
import servent.SimpleServentListener;
import servent.message.LeaveMessage;
import servent.message.Message;
import servent.message.QuitMessage;
import servent.message.util.ExitThread;
import servent.message.util.MessageUtil;

import java.util.Map;
import java.util.concurrent.CyclicBarrier;

public class QuitCommand implements CLICommand {
    private CLIParser parser;
    private SimpleServentListener listener;

    public QuitCommand(CLIParser parser, SimpleServentListener listener) {
        this.parser = parser;
        this.listener = listener;
    }

    @Override
    public String commandName() {
        return "quit";
    }

    @Override
    public void execute(String args) {
        CyclicBarrier barrier = new CyclicBarrier(AppConfig.state.getNodes().size(), new ExitThread());
        System.out.println(AppConfig.state.getNodes().entrySet());
        for (Map.Entry<Integer, NodeInfo> entry : AppConfig.state.getNodes().entrySet()) {
            if (entry.getKey().equals(AppConfig.info.getNodeId())) {
                continue;
            }
            Message quitMessage = new QuitMessage(AppConfig.info.getNodeInfo(), entry.getValue());
            MessageUtil.notifyQuit(quitMessage, barrier);
        }
        Message leaveMessage = new LeaveMessage(AppConfig.info.getNodeInfo(), new NodeInfo(AppConfig.BOOTSTRAP.getPort(), AppConfig.BOOTSTRAP.getIpAddress(), -1, "", ""));
        MessageUtil.notifyQuit(leaveMessage, barrier);
    }
}
