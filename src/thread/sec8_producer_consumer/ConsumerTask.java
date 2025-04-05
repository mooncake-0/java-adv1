package thread.sec8_producer_consumer;

import thread.util.MyLogger;

import static thread.util.MyLogger.*;

public class ConsumerTask implements Runnable {

    private BoundedQueue queue;

    public ConsumerTask(BoundedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        log("[소비 시도]     ? <- " + queue);
        String data = queue.take();
        log("[소비 시도] " + data + " <- " + queue);
    }
}