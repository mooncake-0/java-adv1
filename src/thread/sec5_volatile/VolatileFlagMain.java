package thread.sec5_volatile;

import thread.util.MyLogger;
import thread.util.ThreadUtils;

import static thread.util.MyLogger.*;

public class VolatileFlagMain {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread t = new Thread(task, "work");
        log("runFlag = " + task.runFlag);
        t.start();

        ThreadUtils.sleep(1000);
        log("runFlag를 False 로 변경 시도 : work Thread 중지 예상");
        task.runFlag = false;
        log("실제 runFlag 값 = " + task.runFlag);
    }

    static class MyTask implements Runnable {
        volatile boolean runFlag = true; // 많은 스레드에서 활용하지만 일반 변수

        @Override
        public void run() {
            log("task 시작");

            while (runFlag) { // runFlag 가 false 면 탈출

            }

            log("task 끝");
        }
    }
}
