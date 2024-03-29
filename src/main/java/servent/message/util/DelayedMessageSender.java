package servent.message.util;

import app.AppConfig;
import servent.message.Message;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

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
            Socket sendSocket = new Socket(messageToSend.getReceiver().getIpAddress(), messageToSend.getReceiver().getPort());
            messageToSend.getRoutes().add(messageToSend.getReceiver().getNodeId());
            DataOutputStream outputStream = new DataOutputStream(sendSocket.getOutputStream());
            outputStream.write(messageToSend.toJson().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            sendSocket.close();
        } catch (IOException e) {
            AppConfig.timestampedErrorPrint("Couldn't send message: " + messageToSend.toString());
        }
    }
}
