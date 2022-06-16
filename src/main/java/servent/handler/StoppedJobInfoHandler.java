package servent.handler;

import app.AppConfig;
import app.model.Result;
import servent.message.StoppedJobInfoMessage;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class StoppedJobInfoHandler implements MessageHandler {
    private StoppedJobInfoMessage clientMessage;
    private CyclicBarrier barrier;

    public StoppedJobInfoHandler(StoppedJobInfoMessage clientMessage, CyclicBarrier barrier) {
        this.clientMessage = clientMessage;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
            Result result = clientMessage.getPayload();
            System.out.println("IIIIIIIIIIIIIIIIIIIIIII");
            AppConfig.state.mergeResults(result);
            System.out.println("eeeeeeeeeeeeeeeeeeeeeeeee");
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
