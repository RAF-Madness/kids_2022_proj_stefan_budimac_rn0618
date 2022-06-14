package servent.message.util;

import app.AppConfig;
import servent.message.Message;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarrierMessageSender implements Runnable {
    private Message messageToSend;
    private CyclicBarrier barrier;

    public BarrierMessageSender(Message messageToSend, CyclicBarrier barrier) {
        this.messageToSend = messageToSend;
        this.barrier = barrier;
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
            DataOutputStream outputStream = new DataOutputStream(sendSocket.getOutputStream());
            outputStream.write(messageToSend.toJson().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            sendSocket.close();
            barrier.await();
        } catch (IOException e) {
            AppConfig.timestampedErrorPrint("Couldn't send message: " + messageToSend.toString());
        } catch (BrokenBarrierException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
