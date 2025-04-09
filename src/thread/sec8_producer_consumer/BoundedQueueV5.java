package thread.sec8_producer_consumer;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static thread.util.MyLogger.log;


// ReentrantLock 과 Lock 에 대한 Condition 을 활용하여,
// 생산자 소비자 집합을 분리한다
public class BoundedQueueV5 implements BoundedQueue {

    private final Lock lock = new ReentrantLock();
    private final Condition producerCond = lock.newCondition();
    private final Condition consumerCond = lock.newCondition(); // 락은 하나인데 대기 공간을 분리할 수 있는 기술

    private final int MAX;
    private final Queue<String> queue = new ArrayDeque<>();

    public BoundedQueueV5(int MAX) {
        this.MAX = MAX;
    }


    @Override
    public void put(String data) { // 생산자 용 대기 공간에 넣어주자

        lock.lock();
        try {
            while (queue.size() == MAX) {
                log("[put실패] 큐가 가득 참, [" + Thread.currentThread().getName() + "] 은 대기한다");
                try {
                    producerCond.await(); // 스레드에게 "condition 에세 기다려라" 라고 명령
                    log("[put 중임], [" + Thread.currentThread().getName() + "] 이 깨어남 ");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            queue.offer(data); // 삽입
            log("[put 임계영역 완료 직전]: [" + Thread.currentThread().getName() + "] 작업 완료, notify 호출");
            consumerCond.signal(); // condition 에게 notify 하는거라고 생각하면 된다
        }finally {
            lock.unlock();
        }
    }

    @Override
    public String take() { // 소비자 용 대기 공간에 넣어주자
        lock.lock();
        try {
            while (queue.isEmpty()) {
                log("[take실패] 큐에 데이터가 없음, [" + Thread.currentThread().getName() + "] 은 대기한다");
                try {
                    consumerCond.await();
                    log("[take 중임], [" + Thread.currentThread().getName() + "] 이 깨어남");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            String data = queue.poll();
            log("[take 임계영역 완료 직전]: [" + Thread.currentThread().getName() + "] 작업 완료, notify 호출");
            producerCond.signal();
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
