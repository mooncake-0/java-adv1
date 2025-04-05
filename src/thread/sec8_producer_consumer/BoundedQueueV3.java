package thread.sec8_producer_consumer;

import thread.util.ThreadUtils;

import java.util.ArrayDeque;
import java.util.Queue;

import static thread.util.MyLogger.log;


// 임계 영역 안에서도 I-Lock 을 양보하여 WAITING 상태로 갈 수 있다
public class BoundedQueueV3 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();

    private final int MAX;

    public BoundedQueueV3(int MAX) {
        this.MAX = MAX;
    }

    @Override
    public synchronized void put(String data) {

        while (queue.size() == MAX) { // 1초에 한번씩 확인한다
            log("[put실패] 큐가 가득 참, [" + Thread.currentThread().getName() + "] 은 대기한다");
//            ThreadUtils.sleep(1000);
            try {
                wait(); // RUNNABLE -> WAITING, 락 반납
                log("[put 중임], [" + Thread.currentThread().getName() + "] 이 깨어남 ");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        queue.offer(data); // 삽입
        log("[put 임계영역 완료 직전]: [" + Thread.currentThread().getName() + "] 작업 완료, notify 호출");
        notify(); // 이 notify 는 WAIT 중인 소비자를 위한 것
    }

    @Override
    public synchronized String take() {

        while (queue.isEmpty()) {
            log("[take실패] 큐에 데이터가 없음, [" + Thread.currentThread().getName() + "] 은 대기한다");
//            ThreadUtils.sleep(1000);
            try {
                wait();
                log("[take 중임], [" + Thread.currentThread().getName() + "] 이 깨어남");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        String data = queue.poll();
        log("[take 임계영역 완료 직전]: [" + Thread.currentThread().getName() + "] 작업 완료, notify 호출");
        notify(); // 이 notify 는 큐가 가득차서 대기중인 생산자를 위함
        return data;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
