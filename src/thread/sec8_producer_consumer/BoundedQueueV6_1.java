package thread.sec8_producer_consumer;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static thread.util.MyLogger.log;


// 자바 제공 역할체 BlockingQueue 를 사용한다 (그냥 함수를 사용)
public class BoundedQueueV6_1 implements BoundedQueue {

    private BlockingQueue<String> queue;

    public BoundedQueueV6_1(int MAX) {
        this.queue = new ArrayBlockingQueue<>(MAX);
    }

    @Override
    public void put(String data) { // 생산자 용 대기 공간에 넣어주자
        try {
            queue.put(data);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String take() { // 소비자 용 대기 공간에 넣어주자
        try {
            return queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
