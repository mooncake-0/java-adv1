package thread.sec11_executor_1;

import thread.util.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.sec11_executor_1.ExecutorUtils.*;
import static thread.util.MyLogger.*;

public class ExecutorBasicMain {

    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(2, 2, 0
                , TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>()); // BQ 도 넣어준다

        log("== 초기 상태 ==");
        printState(executorService);
        executorService.execute(new RunnableTask("Task A"));
        executorService.execute(new RunnableTask("Task B")); // max Size 가 2 이기 때문에 여기까지 "없으면" Thread 를 만들고, 그 다음부턴 재사용한다
        executorService.execute(new RunnableTask("Task C")); // 그래서 수행 순서가 A,B 가 완료되어야 C,D 가 수행됨을 볼 수 있다
        executorService.execute(new RunnableTask("Task D"));
        log("== 작업 수행 중 ==");
        printState(executorService);

        ThreadUtils.sleep(3000);
        log("== 작업 수행 완료 ==");
        printState(executorService);

        executorService.close(); // 다 썼으면 종료, shutdown 이라고 한다
        log("== shutdown 완료 ==");
        printState(executorService);

    }
}
