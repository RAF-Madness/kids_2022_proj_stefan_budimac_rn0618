package servent.message.util;

import app.AppConfig;
import servent.message.BasicMessage;
import servent.message.Message;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.CyclicBarrier;

public class MessageUtil {
    public static final boolean MESSAGE_UTIL_PRINTING = true;

    public static Message readMessage(Socket socket) {
        Message clientMessage = null;
        try {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            String json = inputStream.readUTF();
            clientMessage = BasicMessage.fromJson(json);
            socket.close();
        } catch (IOException e) {
            AppConfig.timestampedErrorPrint("Error while reading socket on " + socket.getInetAddress() + ":" + socket.getPort());
        }
        if (MESSAGE_UTIL_PRINTING) {
            AppConfig.timestampedStandardPrint("Got message " + clientMessage);
        }
        return clientMessage;
    }

    public static void sendMessage(Message message) {
        Thread delayedSender = new Thread(new DelayedMessageSender(message));
        delayedSender.start();
    }

    public static void notifyQuit(Message message, CyclicBarrier barrier) {
        Thread barrierSender = new Thread(new BarrierMessageSender(message, barrier));
        barrierSender.start();
    }
}
