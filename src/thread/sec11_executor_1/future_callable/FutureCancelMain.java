package thread.sec11_executor_1.future_callable;

import thread.util.MyLogger;
import thread.util.ThreadUtils;

import java.util.concurrent.*;

import static thread.util.MyLogger.*;
import static thread.util.MyLogger.log;

public class FutureCancelMain {

    private static boolean mayInterruptIfRunning = false;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(new MyTask());
        log("Future.state: " + future.state());

        // 일정 시간 후 취소 시도
        ThreadUtils.sleep(3000); // 3초 뒤
        log("future.cancel(" + mayInterruptIfRunning + ") 호출");
        boolean cancelResult = future.cancel(mayInterruptIfRunning);
        log("cancel(" + mayInterruptIfRunning + ") result : " + cancelResult);

        // 결과 확인
        try {
            log("Future.result: " + future.get());
        } catch (CancellationException exception) {
            log("Future 는 취소되어서 get 을 할 수 없습니다");
        } catch (InterruptedException | ExecutionException exception) {
            exception.printStackTrace();
        }
        executorService.close();
    }

    static class MyTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            try {
                for (int i = 0; i < 10; i++) { // 10초동안 작업
                    log("작업 중 : " + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException exception) {
                log("인터럽트 발생");
                return "Interrupted";
            }
            return "Complete";
        }
    }

}