package thread.sec4_control.interrupt;

import thread.util.MyLogger;
import thread.util.ThreadUtils;

import static thread.util.MyLogger.*;

public class ThreadStopMainV1 {

    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread t1 = new Thread(task, "work thread");
        t1.start();

        ThreadUtils.sleep(4000); // t1 이 작업하는 모습 출력을 위함
        log("작업 중단 지시 runFlag = false 화");
        task.runFlag = false;

    }

    static class MyTask implements Runnable {

        volatile boolean runFlag = true; // 일단 쓰는 volatile, 내가 알기론 캐싱하지 말고 매번 직접 이 값을 사용하라는거였던 것 같은데..

        @Override
        public void run() {
            while (runFlag) { // runFlag True 는 외부에서 바꿔줘야 스레드가 종료된다는 것을 알 수 있다
                log("Task 작업 중");
                ThreadUtils.sleep(3000);
            }
            log("자원 정리");
            log("자원 종료");
        }
    }
}
