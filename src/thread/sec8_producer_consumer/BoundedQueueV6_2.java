package thread.sec8_producer_consumer;

import thread.util.MyLogger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static thread.util.MyLogger.*;


// 자바 제공 역할체 BlockingQueue 를 사용한다 (그냥 함수를 사용)
public class BoundedQueueV6_2 implements BoundedQueue {

    private BlockingQueue<String> queue;

    public BoundedQueueV6_2(int MAX) {
        this.queue = new ArrayBlockingQueue<>(MAX);
    }

    @Override
    public void put(String data) {
        boolean result = queue.offer(data); // 실패시 대기하지 않고 결과를 바로 반환한다
        log("저장 시도 결과 = " + result);
    }

    @Override
    public String take() {
        return queue.poll(); // 가지고 나올게 없으면 NULL 반환
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
