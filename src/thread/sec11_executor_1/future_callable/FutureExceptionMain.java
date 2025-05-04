package thread.sec11_executor_1.future_callable;

import thread.util.MyLogger;
import thread.util.ThreadUtils;

import java.util.concurrent.*;

import static thread.util.MyLogger.*;

public class FutureExceptionMain {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(1);

        Future<Integer> future = es.submit(new ExCallable());
        ThreadUtils.sleep(1000); // 작업 잠시 대기

        try {
            log("future.get() 호출 시도, future.state(): " + future.state()); // 예외 터졌으니 FAILED 상태
            Integer result = future.get(); // get 함수 수행
            log("result val = " + result); //
        } catch (InterruptedException e) { // 대상 스레드가 Interrupt 받는게 아니라 future.get 하는 도중 요청 스레드가 Interrupt 받는 경우
            log("요청 스레드 대기 중 인터럽트 발생");
            throw new RuntimeException(e);
        } catch (ExecutionException e) { // 실행중에 발생하는 예외를 반환해준다. 발생한 예외 그대로 나오긴 어렵고, 대체 예외가 나와준다
            // 근데 나는 Illegal 을 던졌는데?
            // 예외는 원래 Chain 으로 물린다 -> e 를 Throwable 에 두며는 근본 원인을 넘겨주는 것 (이건 기본기)
            log("e = " + e);
            Throwable cause = e.getCause(); // Future 안에서도 원본 예외를 Throwable 로 받아둔다
            log("cause = " + cause);
        }

        es.close();
    }

    static class ExCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            log("Callable 실행, 예외 발생");
            throw new Exception("[예외발생]");
        }
    }
}
