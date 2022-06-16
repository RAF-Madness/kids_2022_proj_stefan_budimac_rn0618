package servent;

import app.AppConfig;
import app.Cancellable;
import servent.handler.*;
import servent.handler.util.JobGenesisSender;
import servent.message.*;
import servent.message.util.MessageUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleServentListener implements Runnable, Cancellable {
    private volatile boolean working = true;

    private final ExecutorService handlerThreadPool = Executors.newWorkStealingPool();

    private CyclicBarrier stoppedJobInfoBarrier;

    public SimpleServentListener() {}

    @Override
    public void run() {
        ServerSocket listenerSocket = null;
        try {
            listenerSocket = new ServerSocket(AppConfig.info.getPort(), 100);
            listenerSocket.setSoTimeout(1000);
        } catch (IOException e) {
            AppConfig.timestampedErrorPrint("Couldn't open listener socket on: " + AppConfig.info.getPort());
            System.exit(0);
        }

        while (working) {
            try {
                Message clientMessage;
                Socket clientSocket = listenerSocket.accept();
                clientMessage = MessageUtil.readMessage(clientSocket);
                MessageHandler messageHandler = new NullHandler(clientMessage);
                switch (clientMessage.getType()) {
                    case CONTACT -> {
                        messageHandler = new ContactHandler((ContactMessage) clientMessage);
                    }
                    case SYSTEM_KNOCK -> {
                        messageHandler = new SystemKnockHandler((SystemKnockMessage) clientMessage);
                    }
                    case WELCOME -> {
                        messageHandler = new WelcomeHandler((WelcomeMessage) clientMessage);
                    }
                    case CONNECTION_REQUEST -> {
                        messageHandler = new ConnectionRequestHandler((ConnectionRequestMessage) clientMessage);
                    }
                    case CONNECTION_RESPONSE -> {
                        messageHandler = new ConnectionResponseHandler((ConnectionResponseMessage) clientMessage);
                    }
                    case ENTER -> {
                        messageHandler = new EnterHandler((EnterMessage) clientMessage);
                    }
                    case REJECT -> {
                        messageHandler = new RejectHandler();
                    }
                    case QUIT -> {
                        messageHandler = new QuitHandler((QuitMessage) clientMessage);
                    }
                    case STOP_SHARE_JOB -> {
                        messageHandler = new StopShareJobHandler((StopShareJobMessage) clientMessage);
                    }
                    case STOPPED_JOB_INFO -> {
                        if (AppConfig.stoppedJobInfoCollected) {
                            stoppedJobInfoBarrier = new CyclicBarrier(AppConfig.state.getNodes().size() - 1, new JobGenesisSender());
                            AppConfig.stoppedJobInfoCollected = false;
                        }
                        messageHandler = new StoppedJobInfoHandler((StoppedJobInfoMessage) clientMessage, stoppedJobInfoBarrier);
                    }
                    case START_JOB_GENESIS -> {
                        messageHandler = new StartJobGenesisHandler((StartJobGenesisMessage) clientMessage);
                    }
                    case APPROACH_CLUSTER -> {
                        messageHandler = new ApproachClusterHandler((ApproachClusterMessage) clientMessage);
                    }
                    case CLUSTER_KNOCK -> {
                        messageHandler = new ClusterKnockHandler((ClusterKnockMessage) clientMessage);
                    }
                    case CLUSTER_WELCOME -> {
                        messageHandler = new ClusterWelcomeHandler((ClusterWelcomeMessage) clientMessage);
                    }
                    case CLUSTER_CONNECTION_REQUEST -> {
                        messageHandler = new ClusterConnectionRequestHandler((ClusterConnectionRequestMessage) clientMessage);
                    }
                    case CLUSTER_CONNECTION_RESPONSE -> {
                        messageHandler = new ClusterConnectionResponseHandler((ClusterConnectionResponseMessage) clientMessage);
                    }
                    case ENTERED_CLUSTER -> {
                        messageHandler = new EnteredClusterHandler((EnteredClusterMessage) clientMessage);
                    }
                    case START_JOB -> {
                        messageHandler = new StartJobHandler((StartJobMessage) clientMessage);
                    }
                    case IMAGE_INFO_REQUEST -> {
                        messageHandler = new ImageInfoRequestHandler((ImageInfoRequestMessage) clientMessage);
                    }
                    case IMAGE_INFO -> {
                        messageHandler = new ImageInfoHandler((ImageInfoMessage) clientMessage);
                    }
                    case JOB_STATUS_REQUEST -> {
                        messageHandler = new JobStatusRequestHandler((JobStatusRequestMessage) clientMessage);
                    }
                    case JOB_STATUS -> {
                        messageHandler = new JobStatusHandler((JobStatusMessage) clientMessage);
                    }
                }
                handlerThreadPool.submit(messageHandler);
            } catch (SocketTimeoutException ignored) {

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stop() {
        this.working = false;
    }
}
