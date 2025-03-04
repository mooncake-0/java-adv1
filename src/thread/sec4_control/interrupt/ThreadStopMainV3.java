package thread.sec4_control.interrupt;

import thread.util.ThreadUtils;

import static thread.util.MyLogger.log;

public class ThreadStopMainV3 {

    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread t1 = new Thread(task, "work thread");
        t1.start();

        ThreadUtils.sleep(100); // 왜 줄였는지는 실행해보면 암
        log("작업 중단 지시 Thread.interrupt 로 지시");
        t1.interrupt(); // interrupt 걸기
        log("work 스레드 interrupt 상태1 = " + t1.isInterrupted());

    }

    static class MyTask implements Runnable {

        @Override
        public void run() {

            while (!Thread.currentThread().isInterrupted()) { // 현 스레드 상태 확인
                log("작업 중");
//                Thread.sleep(3000); // 직접적으로 interrupt 여부를 체킹하기 때문에, interrupt 를 걸리게 하기 위한 WAITING 으로의 전환은 불필요
            }

            // 탈출하면 Interrupt Exception 이나 마찬가지인 설계
            // 하지만 예외가 아니기 때문에 interrupted 상태를 전환해주지 않음!! (따라서 true 를 유지)
            log("work 스레드 interrupt 상태2 = " + Thread.currentThread().isInterrupted());

            log("자원 정리");
            log("자원 종료");
        }
    }
}
