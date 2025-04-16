package thread.sec9_cas.increment;

/*
 MEMO:: 잘못된 코드다 - 단일 스레드 (volatile 불필요), 멀티 스레드 (임계 영역 보장 X)
 */
public class V2VolatileInteger implements IncrementInteger {
    private volatile int value;
    @Override
    public void increment() {
        value++;
    }
    @Override
    public int getValue() {
        return this.value;
    }
}
