package servent.message.util;

import app.AppConfig;
import servent.message.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DelayedMessageSender implements Runnable {
    private Message messageToSend;

    public DelayedMessageSender(Message messageToSend) {
        this.messageToSend = messageToSend;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random() * 1000) + 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (MessageUtil.MESSAGE_UTIL_PRINTING) {
            AppConfig.timestampedStandardPrint("Sending message " + messageToSend);
        }
        try {
            Socket sendSocket = new Socket(messageToSend.getRecieverIpAddress(), messageToSend.getRecieverPort());
            ObjectOutputStream outputStream = new ObjectOutputStream(sendSocket.getOutputStream());
            outputStream.writeObject(messageToSend);
            outputStream.flush();
            sendSocket.close();
        } catch (IOException e) {
            AppConfig.timestampedErrorPrint("Couldn't send message: " + messageToSend.toString());
        }
    }
}
