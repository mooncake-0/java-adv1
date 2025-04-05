package thread.sec8_producer_consumer;

import thread.util.MyLogger;

import java.util.ArrayDeque;
import java.util.Queue;

import static thread.util.MyLogger.*;

// BoundedQueueV1 의 queue 는 공유자원
// > 각 함수가 여러 스레드에서 접근 가능하기 때문에 sync 를 붙인다
// > put 이나 take 모두 인스턴스에 대한 락을 공유하기 때문에 하나 접근하면 다른 함수여도 대기해야 한다
public class BoundedQueueV1 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();

    private final int MAX;

    public BoundedQueueV1(int MAX) {
        this.MAX = MAX;
    }

    @Override
    public synchronized void put(String data) {
        if (queue.size() == MAX) {
            log("[put실패] 큐가 가득 참, 요청 데이터 버림: " + data);
            return;
        }
        queue.offer(data); // 삽입
    }

    @Override
    public synchronized String take() {
        if (queue.isEmpty()) {
            return null;
        }
        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
