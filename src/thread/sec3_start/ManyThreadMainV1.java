package thread.sec3_start;

import static thread.util.MyLogger.*;

public class ManyThreadMainV1 {

    public static void main(String[] args) {
        log("main() START!");

        // 동일한 작업 "인스턴스" 를 전달한다
        HelloRunnable runnable = new HelloRunnable();

        Thread t1 = new Thread(runnable);
        t1.start();

        Thread t2 = new Thread(runnable);
        t2.start();

        Thread t3 = new Thread(runnable);
        t3.start();

        log("main() END!");
    }
}
