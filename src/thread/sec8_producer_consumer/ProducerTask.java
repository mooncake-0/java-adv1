package thread.sec8_producer_consumer;

import thread.util.MyLogger;

import static thread.util.MyLogger.*;

public class ProducerTask implements Runnable{

    private BoundedQueue queue; // 어디서 가져올건지
    private String req; // 넣은 데이터

    public ProducerTask(BoundedQueue queue, String req) {
        this.queue = queue;
        this.req = req;
    }

    @Override
    public void run() {
        log("[생산 시도] " + req + " -> " + queue);
        queue.put(req);
        log("[생산 완료] " + queue);
    }
}
