package thread.sec10_동시성컬렉션.simple;

import thread.sec4_control.interrupt.ThreadStopMainV1;
import thread.util.MyLogger;

import static thread.util.MyLogger.*;

public class SimpleListMainV1 {

    public static void main(String[] args) throws InterruptedException {
//        test(new BasicList());
        test(new SyncList());
    }

    private static void test(SimpleList list) throws InterruptedException {
        log(list.getClass().getSimpleName());

        // A 를 리스트에 저장하는 스레드
        Runnable aTh = new Runnable() {
            @Override
            public void run() {
                list.add("A");
                log("Thread-1 : adds A");
            }
        };

        // A 를 리스트에 저장하는 스레드
        Runnable bTh = new Runnable() {
            @Override
            public void run() {
                list.add("B");
                log("Thread-2 : adds B");
            }
        };

        Thread th1 = new Thread(aTh, "Thread-1");
        Thread th2 = new Thread(bTh, "Thread-2");
        th1.start();
        th2.start();

        th1.join();
        th2.join();

        log("결과:: " + list);
    }
}
