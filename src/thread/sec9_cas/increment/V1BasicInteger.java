package thread.sec9_cas.increment;

public class V1BasicInteger implements IncrementInteger {
    private int value;
    @Override
    public void increment() {
        value++;
    }

    @Override
    public int getValue() {
        return this.value;
    }
}
