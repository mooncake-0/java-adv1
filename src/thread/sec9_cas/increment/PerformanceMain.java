package thread.sec9_cas.increment;

public class PerformanceMain {

    static long CNT = 100_000_000;

    public static void main(String[] args) {

        test(new V1BasicInteger());
        test(new V2VolatileInteger());
        test(new V3SyncInteger());
        test(new V4AtomicInteger());

    }

    private static void test(IncrementInteger integer) {

        long startMs = System.currentTimeMillis();

        for (long i = 0; i < CNT; i++) {
            integer.increment();
        }

        long endMs = System.currentTimeMillis();
        System.out.println(integer.getClass().getSimpleName() + ":: ms = " + (endMs - startMs));
    }
}
