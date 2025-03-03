package thread.sec3_start.runnable_만드는법;

import static thread.util.MyLogger.*;

// 중첩클래스를 활용한 Runnable 활용
public class InnerRunnableMainV1 {

    public static void main(String[] args) {

        log("main() sec3_start!");

        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();

        log("main() end!");

    }

    // 여러 곳에서 안 쓰고, 이 public class 안에서만 쓸 것 같으면 중첩클래스를 활용하는게 좋다
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            log("run()!");
        }
    }
}
