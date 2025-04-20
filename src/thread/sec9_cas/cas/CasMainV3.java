package thread.sec9_cas.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static thread.util.MyLogger.log;

public class CasMainV3 {

    private static final int THREAD_CNT = 2;

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println("start val = " + atomicInteger.get());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                myIncrementAndGet(atomicInteger);
            }
        };

        List<Thread> ths = new ArrayList<>();
        for (int i = 0; i < THREAD_CNT; i++) {
            Thread thread = new Thread(runnable);
            ths.add(thread);
            thread.start();
        }

        for (Thread th : ths) {
            th.join();
        }

        int result = atomicInteger.get();
        System.out.println("result = " + result);

    }

    // V2에서 만들었던 것
    private static int myIncrementAndGet(AtomicInteger atomicInteger) {

        int getValue;
        boolean result;

        do { // 먼저 시도하고 while 진행
            getValue = atomicInteger.get(); // 현재 값 읽는다
            log("getValue: " + getValue);

            result = atomicInteger.compareAndSet(getValue, getValue + 1); // update 명령을 치는 시점에 같다면 올려라 - CAS 연산 지원 (Thread-safe)
            log("result: " + result);

        } while (!result);

        // 연산 성공시 빠져나옴
        // getValue + 1 을 하는 이유 : result 찍고 여기까지 오는 중 타 스레드가 또 atomicInteger 를 바꿀 수 도 있다
        // 따라서 이 함수의 결과를 반환해주기 위해 getValue +1 을 해준다
        return getValue + 1;
    }

}
