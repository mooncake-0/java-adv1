package thread.sec11_executor_2.shutdown;

import thread.sec11_executor_1.ExecutorBasicMain;
import thread.sec11_executor_1.RunnableTask;
import thread.util.MyLogger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static thread.sec11_executor_1.ExecutorUtils.*;
import static thread.util.MyLogger.*;

public class ExecutorShutdownMain {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(new RunnableTask("TaskA"));
        es.execute(new RunnableTask("TaskB"));
        es.execute(new RunnableTask("TaskC"));
        es.execute(new RunnableTask("longTask", 100_000)); // 100초 걸림 -> 이상한 상황임으로 설정
        printState(es);

        log("====== shutdown 시작 ======");
        shutdownAndAwaitTermination(es);
        log("====== shutdown 완료 ======");
    }

    // 공식 메뉴얼의 방식이기도 하다 (약간 편하게)
    private static void shutdownAndAwaitTermination(ExecutorService es) {

        // 정상종료 먼저 수행
        es.shutdown(); // non-blocking, 새로운 작업을 받지 않고 나머지는 종료를 대기해보자 -> 작업 내부에서 Thread.sleep 이 있으므로 인터럽트가 발생한다

        try {
            boolean isFinished = es.awaitTermination(10, TimeUnit.SECONDS); // 10초간 요청스레드를 대기시켜본다. 결과적으로 종료되었는지? 를 확인.
            if (!isFinished) { // 문제가 발생함 -> 정상 종료가 너무 오래걸린다
                log("서비스 정상 종료 실패 :: 강제 종료 진행");
                es.shutdownNow();
                // 이후 작업 취소까지도 시간이 필요
                if (!es.awaitTermination(10, TimeUnit.SECONDS)) {
                    log("shutdown 시도 종료 :: ES는 현재 종료되지 않았습니다");
                }
            }
        } catch (InterruptedException e) { // 여긴 뭐 요구사항에 맞게 구현하기 나름
            // 대기중인 요청 스레드가 인터럽트 걸릴 수도 있다
            es.shutdownNow();
            throw new RuntimeException(e);
        }
    }


}
