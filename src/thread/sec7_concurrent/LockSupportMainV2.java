package thread.sec7_concurrent;

import thread.util.ThreadUtils;

import java.util.concurrent.locks.LockSupport;

import static thread.util.MyLogger.log;

public class LockSupportMainV2 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new ParkTest(), "T1");
        t1.start();

        // 잠시 대기하여 Thread1 이 Park 에 빠질 시간을 줌
        ThreadUtils.sleep(100);
        log("T1 의 상태 조회: " + t1.getState()); // t1 의 상태가 WAITING 임을 알 수 있다
    }


    // 이번엔 스스로 깨어난다
    static class ParkTest implements Runnable {
        @Override
        public void run() {
            log("parkNanos 시작");
            LockSupport.parkNanos(2000_000000); // 2초 뒤에 깨어난다
            log("2초 뒤 parkNanos 알아서 종료, state: " + Thread.currentThread().getState());
            log("인터럽트 상태: " + Thread.currentThread().isInterrupted());
        }
    }
}
