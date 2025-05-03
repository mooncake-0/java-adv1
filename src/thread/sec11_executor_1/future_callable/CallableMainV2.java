package thread.sec11_executor_1.future_callable;

import thread.util.ThreadUtils;

import java.util.Random;
import java.util.concurrent.*;

import static thread.util.MyLogger.log;

// V1 에서 분석하기 쉽게 log 들을 추가한다
public class CallableMainV2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 우선 Callable 을 쓰려면 Executor 가 필요하다
        ExecutorService es = Executors.newFixedThreadPool(1);// 이건 이전에 한거랑 똑같은데, core & max = 1 인 것
        Future<Integer> future = es.submit(new MyCallable());// Callable 을 넘기고 Future 을 반환받도록 하는 submit() 함수
        log("future 즉시 반환, future = " + future);


        log("future.get() - [블로킹] 메소드 호출 시작 -> main 스레드 WAITING");
        Integer result = future.get(); // *메인 스레드는 여기서 기다린다
        log("future.get() - [블로킹] 메소드 호출 완료 -> main 스레드 RUNNABLE");

        log("result: " + result);
        log("future 완료, future = " + future);
        es.close();

    }

    static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() {
            log("Runnable 시작");

            ThreadUtils.sleep(2000); // 작업 소요 시간 2초라고 가정
            int val = new Random().nextInt(10);

            log("create val: " + val);
            log("Runnable 종료");
            return val; // 스레드의 결과를 어떻게 가져올 수 있는걸까?
        }
    }
}
