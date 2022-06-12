package servent.message.util;

import app.AppConfig;
import app.model.NodeInfo;
import servent.message.Message;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class DelayedMessageSender implements Runnable {
    private Message messageToSend;
    private NodeInfo receiverInfo;

    public DelayedMessageSender(Message messageToSend, NodeInfo receiverInfo) {
        this.messageToSend = messageToSend;
        this.receiverInfo = receiverInfo;
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
            Socket sendSocket = new Socket(receiverInfo.getIpAddress(), receiverInfo.getPort());
            DataOutputStream outputStream = new DataOutputStream(sendSocket.getOutputStream());
            outputStream.writeUTF(messageToSend.toJson());
            outputStream.flush();
            sendSocket.close();
        } catch (IOException e) {
            AppConfig.timestampedErrorPrint("Couldn't send message: " + messageToSend.toString());
        }
    }
}
