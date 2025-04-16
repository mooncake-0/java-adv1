package thread.sec9_cas.increment;

public class V3SyncInteger implements IncrementInteger {
    private int value;
    @Override
    public synchronized void increment() {
        value++;
    }

    @Override
    public synchronized int getValue() {
        return this.value;
    }
}
