package thread.sec8_producer_consumer;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static thread.util.MyLogger.log;


// Sync 함수와 I-Lock 을 활용하지 않고 직접 관리 (Explicit Lock)
// - Condition 으로 Wait Set 도 직접 관리해보는 영역
// - 참고로 I-Lock 도 배웠듯이 대기 집합이 있으나, 이건 개발자가 제어할 수 없다. wait(), notify() 를 활용한다
public class BoundedQueueV4 implements BoundedQueue {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition(); // Lock 에서 가져오는데, 해당 락에 대한 대기집합이 된다
    private final int MAX;
    private final Queue<String> queue = new ArrayDeque<>();

    public BoundedQueueV4(int MAX) {
        this.MAX = MAX;
    }

    // 일단 똑같은데, 자바 기본 -> 직접 제어할 수 있는 concurrent 패키지로 이동
    @Override
    public void put(String data) {

        lock.lock();
        try {
            while (queue.size() == MAX) {
                log("[put실패] 큐가 가득 참, [" + Thread.currentThread().getName() + "] 은 대기한다");
                try {
//                    wait(); // RUNNABLE -> WAITING, 락 반납 (이건 자바 내부 객체)
                    condition.await(); // 스레드에게 "condition 에세 기다려라" 라고 명령
                    log("[put 중임], [" + Thread.currentThread().getName() + "] 이 깨어남 ");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            queue.offer(data); // 삽입
            log("[put 임계영역 완료 직전]: [" + Thread.currentThread().getName() + "] 작업 완료, notify 호출");
//            notify(); // 이 notify 는 WAIT 중인 소비자를 위한 것
            condition.signal(); // condition 에게 notify 하는거라고 생각하면 된다
        }finally {
            lock.unlock();
        }
    }

    @Override
    public String take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                log("[take실패] 큐에 데이터가 없음, [" + Thread.currentThread().getName() + "] 은 대기한다");
                try {
//                    wait();
                    condition.await();
                    log("[take 중임], [" + Thread.currentThread().getName() + "] 이 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            String data = queue.poll();
            log("[take 임계영역 완료 직전]: [" + Thread.currentThread().getName() + "] 작업 완료, notify 호출");
//            notify(); // 이 notify 는 큐가 가득차서 대기중인 생산자를 위함
            condition.signal();
            return data;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
