package thread.sec4_control.join;

import thread.util.MyLogger;
import thread.util.ThreadUtils;

import static thread.util.MyLogger.*;

public class JoinMainV0 {

    public static void main(String[] args) {
        log("Start");

        Thread t1 = new Thread(new Job(), "thread-1");
        Thread t2 = new Thread(new Job(), "thread-2");
        t1.start();
        t2.start();

        log("End");
    }

    static class Job implements Runnable {
        @Override
        public void run() { // 2초짜리의 작업을 하는 스레드
            log("작업 시작");
            ThreadUtils.sleep(1000);
            log("작업 끝");
        }
    }
}
