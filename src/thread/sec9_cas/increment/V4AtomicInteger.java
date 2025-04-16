package thread.sec9_cas.increment;

import java.util.concurrent.atomic.AtomicInteger;

// 동기화 처리를 안해도 된다. 내부적으로 구현되어 있음
// sync 함수보다 성능이 우수하다
public class V4AtomicInteger implements IncrementInteger {
    private AtomicInteger atomicValue = new AtomicInteger(0);

    @Override
    public void increment() {
        atomicValue.incrementAndGet();
    }

    @Override
    public int getValue() {
        return atomicValue.get();
    }
}
