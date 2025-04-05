package thread.sec8_producer_consumer;

import thread.util.ThreadUtils;

import java.util.ArrayDeque;
import java.util.Queue;

import static thread.util.MyLogger.log;


// BoundedQueue V1 은 스레드가 대기하지 않는 대표적인 생산자/소비자 문제가 있다
// 스레드를 처음으로 대기시켜보자
public class BoundedQueueV2 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();

    private final int MAX;

    public BoundedQueueV2(int MAX) {
        this.MAX = MAX;
    }

    @Override
    public synchronized void put(String data) {

        while (queue.size() == MAX) { // 1초에 한번씩 확인한다
            log("[put실패] 큐가 가득 참, [" + Thread.currentThread().getName() + "] 은 대기한다");
            ThreadUtils.sleep(1000);
        }

//        if (queue.size() == MAX) {
//            log("[put실패] 큐가 가득 참, 요청 데이터 버림: " + data);
//            return;
//        }

        queue.offer(data); // 삽입
    }

    @Override
    public synchronized String take() {

        while (queue.isEmpty()) {
            log("[take실패] 큐에 데이터가 없음, [" + Thread.currentThread().getName() + "] 은 대기한다");
            ThreadUtils.sleep(1000);
        }

//        if (queue.isEmpty()) {
//            return null;
//        }

        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
