package thread.sec9_cas.spinlock;

import thread.util.MyLogger;

import static thread.util.MyLogger.*;

public class SpinLockMain {
    public static void main(String[] args) {

//        SpinLockBad spinLock = new SpinLockBad();
//        MySpinLockTrial spinLock = new MySpinLockTrial();
        SpinLock spinLock = new SpinLock();
        Runnable runnable = new Runnable() {
            public void run() {
                spinLock.lock();
                try {
                    // 임계 영역
                    log("비즈니스 로직 실행");
                } finally { // 무조건 반환을 위함
                    spinLock.unlock();
                }
            }
        };

        Thread t1 = new Thread(runnable, "t1");
        Thread t2 = new Thread(runnable, "t2");

        t1.start();
        t2.start();

    }
}
