package thread.sec8_producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static thread.util.MyLogger.log;


// 자바 제공 역할체 BlockingQueue 를 사용한다 (그냥 함수를 사용)
// 바로 반환이 아닌, 일정 시간 대기 후 결과를 반환한다
public class BoundedQueueV6_3 implements BoundedQueue {

    private BlockingQueue<String> queue;

    public BoundedQueueV6_3(int MAX) {
        this.queue = new ArrayBlockingQueue<>(MAX);
    }

    @Override
    public void put(String data) {
        try {
            boolean result = queue.offer(data, 1, TimeUnit.SECONDS); // 대기 시간도 설정 가능
            log("저장 시도 결과 = " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String take() {
        try {
            return queue.poll(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
