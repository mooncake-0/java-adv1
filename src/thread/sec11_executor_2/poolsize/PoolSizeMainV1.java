package thread.sec11_executor_2.poolsize;

import thread.sec11_executor_1.ExecutorUtils;
import thread.sec11_executor_1.RunnableTask;
import thread.util.MyLogger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.sec11_executor_1.ExecutorUtils.*;
import static thread.util.MyLogger.*;

public class PoolSizeMainV1 {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4
                , 3000, TimeUnit.MILLISECONDS, workQueue);

        printState(executor);

        executor.execute(new RunnableTask("TASK1"));
        printState(executor, "TASK1");

        executor.execute(new RunnableTask("TASK2"));
        printState(executor, "TASK2");

        executor.execute(new RunnableTask("TASK3"));
        printState(executor, "TASK3");

        executor.execute(new RunnableTask("TASK4"));
        printState(executor, "TASK4");

        executor.execute(new RunnableTask("TASK5"));
        printState(executor, "TASK5");

        executor.execute(new RunnableTask("TASK6"));
        printState(executor, "TASK6");
        try {
            executor.execute(new RunnableTask("TASK7"));
            printState(executor, "TASK7");
        } catch (Exception e) {
            log("dd");
        }

        // -------------
        Thread.sleep(3000);
        log("====== 작업 수행 완료 ======");
        printState(executor);

        Thread.sleep(3000);
        log("====== 초과되어 생성된 maxPool 들의 대기 시간 초과 ======");
        printState(executor);

        executor.close();

    }
}
