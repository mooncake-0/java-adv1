package thread.sec9_cas.increment;

import thread.util.ThreadUtils;

import java.util.ArrayList;
import java.util.List;

public class IncrementThreadMain {

    public static final int THREAD_CNT = 1000;

    public static void main(String[] args) throws InterruptedException {

        test(new V1BasicInteger());
        test(new V2VolatileInteger());
        test(new V3SyncInteger());
        test(new V4AtomicInteger());

    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ThreadUtils.sleep(10); // 너무 빨리 실행되기 때문에, 다른 스레드들과 동시에 실행되게 하기 위함
                incrementInteger.increment();
            }
        };

        List<Thread> ths = new ArrayList<>();
        for (int i = 0; i < THREAD_CNT; i++) {
            Thread th = new Thread(runnable);
            ths.add(th);
            th.start();
        }

        for (Thread th : ths) {
            th.join();
        }

        int res = incrementInteger.getValue();
        System.out.println(incrementInteger.getClass().getName() + "'s result :: " + res);

    }
}
