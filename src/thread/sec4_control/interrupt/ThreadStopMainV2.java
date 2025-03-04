package thread.sec4_control.interrupt;

import thread.util.ThreadUtils;

import static thread.util.MyLogger.log;

public class ThreadStopMainV2 {

    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread t1 = new Thread(task, "work thread");
        t1.start();

        ThreadUtils.sleep(4000); // t1 이 작업하는 모습 출력을 위함
        log("작업 중단 지시 Thread.interrupt 로 지시");
        t1.interrupt(); // Interrupt 상태로 전환. T1 이 WAITING / TIMED_WAITING 으로 전환될 시 Interrupt 를 발생시킨다
        log("work 스레드 interrupt 상태1 = " + t1.isInterrupted());

    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    log("Task 작업 중");
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) { // Sleep 도중 interrupt 가 걸리면 빠져 나오게 된다
                log("work 스레드 interrupt 상태2 = " + Thread.currentThread().isInterrupted()); // 현재 interrupt 걸렸는지 확인
                log("interrupt msg = " + e.getMessage());
                log("Work Thread State = " + Thread.currentThread().getState());
            }

            log("자원 정리");
            log("자원 종료");
        }
    }
}
