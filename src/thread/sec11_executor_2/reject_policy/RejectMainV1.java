package thread.sec11_executor_2.reject_policy;

import thread.sec11_executor_1.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectMainV1 {
    public static void main(String[] args) {

        // 바로 거절되는걸 보기 위해 버퍼 0 짜리로 만듬
        ExecutorService executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS
                , new SynchronousQueue<>(), new ThreadPoolExecutor.AbortPolicy());

        executor.submit(new RunnableTask("TASK1"));
        executor.submit(new RunnableTask("TASK2")); // 거절당할 것임

        executor.close();
    }
}
