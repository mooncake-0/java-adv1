package thread.sec11_executor_1.future_callable;

import thread.util.ThreadUtils;

import java.util.Random;

import static thread.util.MyLogger.*;

// Runnable 이 작업의 결과를 가져오는 방법 (join 활용)
// - 불편한 이유를 설명
public class RunnableMain {
    public static void main(String[] args) throws InterruptedException {
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable, "th-1");
        thread.start();
        thread.join(); // 작업 결과 대기

        int result = runnable.val;
        System.out.println("작업 결과 값: " + result);

    }

    static class MyRunnable implements Runnable {
        int val;

        public void run() {
            log("Runnable 시작");

            ThreadUtils.sleep(2000); // 작업 소요 시간 2초라고 가정
            val = new Random().nextInt(10);
            log("create val: " + val);
            log("Runnable 종료");
        }
    }
}
