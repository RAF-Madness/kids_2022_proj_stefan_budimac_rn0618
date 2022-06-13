package servent;

import app.AppConfig;
import app.Cancellable;
import servent.handler.*;
import servent.message.Message;
import servent.message.util.MessageUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleServentListener implements Runnable, Cancellable {
    private volatile boolean working = true;

    private final ExecutorService handlerThreadPool = Executors.newWorkStealingPool();

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
                        messageHandler = new ContactHandler(clientMessage);
                    }
                    case SYSTEM_KNOCK -> {
                        messageHandler = new SystemKnockHandler(clientMessage);
                    }
                    case WELCOME -> {
                        messageHandler = new WelcomeHandler(clientMessage);
                    }
                    case CONNECTION_REQUEST -> {
                        messageHandler = new ConnectionRequestHandler(clientMessage);
                    }
                    case CONNECTION_RESPONSE -> {
                        messageHandler = new ConnectionResponseHandler(clientMessage);
                    }
                    case ENTER -> {
                        messageHandler = new EnterHandler(clientMessage);
                    }
                    case REJECT -> {
                        messageHandler = new RejectHandler();
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
