package thread.sec4_control;

import static thread.util.MyLogger.*;


public class ThreadStateMain {

    public static void main(String[] args) throws InterruptedException {

        Thread myThread = new Thread(new MyRunnable(), "MyThread");
        log("myThread.state = " + myThread.getState());
        log("myThread.sec3_start()");
        myThread.start();

        // Main 을 재우고 찍어보는게 좋다 -> 바로 다음줄을 MyThread 가 자기 전에 찍어버릴 수 있기 때문
        Thread.sleep(1000);
        log("myThread.state3 = " + myThread.getState()); // run 에서 자고 있음. 여기서 찍어보는 것 __ TIMED_WAITING 상태 확인 가능

        Thread.sleep(4000); // myThread 의 run 종료를 기다려보자
        log("myThread.state5 = " + myThread.getState()); // run 이 실행 완료됨 __ TERMINATED 상태 전이 확인 가능

    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            try {

                log("MyThread run() sec3_start!!--------------------");
                log("myThread.state2 = " + Thread.currentThread().getState()); // NEW -> RUNNABLE 상태 전이 확인

                log("MyThread sleep() sec3_start ---");
                Thread.sleep(3000); // 여기서 자고 있으니까, 다음에 getState 을 찍으면 당연히 RUNNABLE 임.. 다른 곳에서 찍어야 함
                log("MyThread sleep() end ---");

                log("myThread.state4 = " + Thread.currentThread().getState()); // TIMED_WAITING 에서 시간 종료로 다시 RUNNABLE 전이 확인

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            log("MyThread run() end!!--------------------");
        }
    }
}
