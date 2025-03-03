package thread.sec3_start;

import static thread.util.MyLogger.log;

public class ManyThreadMainV2 {

    public static void main(String[] args) {
        log("main() START!");

        // 동일한 작업 "인스턴스" 를 전달한다
        HelloRunnable runnable = new HelloRunnable();
        for (int i = 0; i < 100; i++) {
            Thread ts = new Thread(runnable);
            ts.start();
        }

        log("main() END!");
    }
}
