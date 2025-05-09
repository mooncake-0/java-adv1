package thread.sec11_executor_1;


import thread.util.MyLogger;
import thread.util.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static thread.util.MyLogger.*;

// 스레드 상태들, 스레드 몇 개인지 이런 것들을 편리하게 출력해보기 위한 도구
public abstract class ExecutorUtils {

    public static void printState(ExecutorService executorService) {

        if (executorService instanceof ThreadPoolExecutor poolExecutor) { // 캐스팅 변환
            int pool = poolExecutor.getPoolSize();
            int active = poolExecutor.getActiveCount();
            int queuedTask = poolExecutor.getQueue().size(); // 생산자/소비자 버퍼가 안에 들어있다
            long completedTask = poolExecutor.getCompletedTaskCount();

            log("[pool= " + pool + ", active= " + active + ", queuedTask= " + queuedTask + ", completedTask= " + completedTask + "]");
        } else {
            log(executorService);
        }
    }

    // 특정 Task 가 들어와서 발생시킨 변화를 보여주기 위함
    public static void printState(ExecutorService executorService, String taskName) {

        if (executorService instanceof ThreadPoolExecutor poolExecutor) { // 캐스팅 변환
            int pool = poolExecutor.getPoolSize();
            int active = poolExecutor.getActiveCount();
            int queuedTask = poolExecutor.getQueue().size(); // 생산자/소비자 버퍼가 안에 들어있다
            long completedTask = poolExecutor.getCompletedTaskCount();

            log(taskName + " -> [pool= " + pool + ", active= " + active + ", queuedTask= " + queuedTask + ", completedTask= " + completedTask + "]");
        } else {
            log(executorService);
        }
    }
}
