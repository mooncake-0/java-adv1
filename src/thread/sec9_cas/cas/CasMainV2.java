package thread.sec9_cas.cas;

import thread.util.MyLogger;

import java.util.concurrent.atomic.AtomicInteger;

import static thread.util.MyLogger.*;

public class CasMainV2 {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start val = " + atomicInteger.get());

//        int res = atomicInteger.incrementAndGet(); // 해당 함수는 1을 Thread-Safe 하게 증가시켜준다
//        System.out.println("res = " + res);

        int res = myIncrementAndGet(atomicInteger);
        System.out.println("res = " + res);

    }

    // 만약 CompareAndSet 을 하는 시점에서, 다른 Thread 가 AtomicInteger 의 값을 변경해 놓았었다면?? -> False 가 반환되고 수행되지 않는다
    // while 문이니까 다시 수행할 것이다 - 이 때 타 thread 의 방해가 없다면 정상적으로 수행된다
    // * 락을 사용하지 않고 CAS 연산을 사용해서 동시성을 보장하는 상태에서 값을 변경한 모습 *
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
        return getValue + 1;
    }

}
