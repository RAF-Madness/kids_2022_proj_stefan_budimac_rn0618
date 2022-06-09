package servent.message.util;

import app.AppConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import servent.message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MessageUtil {
    public static final boolean MESSAGE_UTIL_PRINTING = true;

    public static Message readMessage(Socket socket) {
        Message clientMessage = null;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            String json = (String) inputStream.readObject();
            ObjectMapper mapper = new ObjectMapper();
            clientMessage = mapper.readValue(json, Message.class);
            socket.close();
        } catch (IOException e) {
            AppConfig.timestampedErrorPrint("Error while reading socket on " + socket.getInetAddress() + ":" + socket.getPort());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
}
