package thread.sec9_cas.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CasMainV1 {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start val = " + atomicInteger.get());

        boolean result1 = atomicInteger.compareAndSet(0, 100); // 비교하고 값이 맞다면 교체해라
        System.out.println("result1 = " + result1 + ", value = " + atomicInteger.get());

        boolean result2 = atomicInteger.compareAndSet(0, 500); // 비교하고 값이 맞다면 교체해라
        System.out.println("result1 = " + result2 + ", value = " + atomicInteger.get());

    }
}
