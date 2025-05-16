package thread.sec11_executor_2.poolsize;


import thread.sec11_executor_1.ExecutorUtils;
import thread.sec11_executor_1.RunnableTask;
import thread.util.MyLogger;

import java.util.concurrent.*;

import static thread.sec11_executor_1.ExecutorUtils.*;
import static thread.util.MyLogger.*;

// 캐시 풀 전략
public class PoolSizeMainV3 {

    public static void main(String[] args) throws InterruptedException {

//        ExecutorService es = Executors.newCachedThreadPool(); - keepAliveTime 제어를 위해
        ExecutorService es = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 3, TimeUnit.SECONDS, new SynchronousQueue<>());
        log("pool 생성");
        printState(es);

        for (int i = 0; i < 4; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask(taskName));
            printState(es, taskName);
        }

        Thread.sleep(3000);
        log("작업 수행 완료");
        printState(es);

        es.close();
        log("종료");
    }

}
