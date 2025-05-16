package thread.sec11_executor_2.reject_policy;

import thread.sec11_executor_1.RunnableTask;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static thread.util.MyLogger.log;

// 사용자 정의
public class RejectMainV3 {
    public static void main(String[] args) {

        // 바로 거절되는걸 보기 위해 버퍼 0 짜리로 만듬
        ExecutorService executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS
//                , new SynchronousQueue<>(), new ThreadPoolExecutor.DiscardPolicy());
                , new SynchronousQueue<>(), new MyRejectedExecutionHandler());

        executor.submit(new RunnableTask("TASK1"));
        executor.submit(new RunnableTask("TASK2")); // 거절당할 것임
        executor.submit(new RunnableTask("TASK3")); // 거절당할 것임
        executor.submit(new RunnableTask("TASK4")); // 거절당할 것임
        executor.close();
    }

    static class MyRejectedExecutionHandler implements RejectedExecutionHandler {

        static AtomicInteger cnt = new AtomicInteger(0);

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // 실행은 중요하지 않은데, 거절된 작업이 몇갠지는 알고 있자.
            int i = cnt.incrementAndGet();
            log("[경고] 거절된 누적 작업 수 : " + i);
        }
    }
}
