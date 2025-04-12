package thread.sec8_producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import static thread.util.MyLogger.log;


// 자바 제공 역할체 BlockingQueue 를 사용한다 (그냥 함수를 사용)
// 바로 반환이 아닌, 일정 시간 대기 후 결과를 반환한다
public class BoundedQueueV6_4 implements BoundedQueue {

    private BlockingQueue<String> queue;

    public BoundedQueueV6_4(int MAX) {
        this.queue = new ArrayBlockingQueue<>(MAX);
    }

    @Override
    public void put(String data) {
        queue.add(data); // 넣을 공간이 없으면 IllegalStateException 을 터뜨린다 (예외를 잡어서 처리)
    }

    @Override
    public String take() {
        return queue.remove(); // 가져올 데이터가 없으면 NoSuchElementException 을 터뜨린다 (예외를 잡어서 처리)
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
