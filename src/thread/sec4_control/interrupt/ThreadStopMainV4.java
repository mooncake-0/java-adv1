package thread.sec4_control.interrupt;

import thread.util.ThreadUtils;

import static thread.util.MyLogger.log;


// 완성
public class ThreadStopMainV4 {

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

            while (!Thread.interrupted()) { // 이전에 isInterrupted() 상태 확인과 다르게, 상태를 확인 후 True 면 False 로 변환해주는 함수
                log("작업 중"); // 작업중이다가 interrupt 상태를 인지하고, while 조건에 맞지 않아 빠져나옴
            } // 그리고 현 스레드의 interrupt 를 false 로 바꿔준다

            log("work 스레드 interrupt 상태2 = " + Thread.currentThread().isInterrupted()); // false 반환을 확인한다!
            log("자원 정리");
            log("자원 종료");
        }
    }
}
