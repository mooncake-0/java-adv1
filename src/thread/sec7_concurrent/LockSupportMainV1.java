package thread.sec7_concurrent;

import thread.util.MyLogger;
import thread.util.ThreadUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

import static thread.util.MyLogger.*;

public class LockSupportMainV1 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new ParkTest(), "T1");
        t1.start();

        // 잠시 대기하여 Thread1 이 Park 에 빠질 시간을 줌
        ThreadUtils.sleep(100);
        log("T1 의 상태 조회: " + t1.getState()); // t1 의 상태가 WAITING 임을 알 수 있다

        log("main 함수가 T1 을 깨운다 : unpark!");
//        LockSupport.unpark(t1);
//        log("T1을 깨운 후 상태 조회: " + t1.getState()); // t1 의 상태가 WAITING 임을 알 수 있다
        t1.interrupt();
        log("T1을 깨운 후 상태 조회: " + t1.getState()); // t1 의 상태가 WAITING 임을 알 수 있다
    }


    static class ParkTest implements Runnable {

        @Override
        public void run() {
            log("park 시작");
            LockSupport.park(); // 호출하는 스레드는 WAITING - 토스 문제에서 활용되었으면 좋았을 수도?
            log("park 종료, state: " + Thread.currentThread().getState());
            log("인터럽트 상태: " + Thread.currentThread().isInterrupted());
        }
    }
}
