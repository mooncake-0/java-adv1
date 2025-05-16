package thread.sec11_executor_2.poolsize;


import thread.sec11_executor_1.RunnableTask;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;
import static thread.sec11_executor_1.ExecutorUtils.printState;
import static thread.util.MyLogger.log;

// 사용자 정의 풀 전략
public class PoolSizeMainV4 {

//    static final int TASK_SIZE = 1100; // 일반
//    static final int TASK_SIZE = 1200; // 긴급
    static final int TASK_SIZE = 1201; // 거절


    public static void main(String[] args) throws InterruptedException {

        ExecutorService es = new ThreadPoolExecutor(100, 200
                , 60, SECONDS, new ArrayBlockingQueue<>(1000));
        printState(es);

        long start = System.currentTimeMillis();

        for (int i = 0; i < TASK_SIZE; i++) {
            String taskName = "TASK" + i;
            try {
                es.execute(new RunnableTask(taskName));
                printState(es, taskName);
            } catch (RejectedExecutionException e) {
                log(taskName + " -> " + e);
            }
        }

        es.close();
        long end = System.currentTimeMillis();
        log("time: " + (end - start));
    }

}
