package servent;

import app.Cancellable;

public class SimpleServentListener implements Runnable, Cancellable {
    private volatile boolean working = true;

    public SimpleServentListener() {}

    @Override
    public void run() {

    }

    @Override
    public void stop() {
        this.working = false;
    }
}
