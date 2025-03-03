package thread.sec4_control.join;

import thread.util.MyLogger;
import thread.util.ThreadUtils;

import static thread.util.MyLogger.*;

public class JoinTest {

    public static void main(String[] args) throws Exception {

        Thread t1 = new Thread(new MyTask(), "t1");
        Thread t2 = new Thread(new MyTask(), "t2");
        Thread t3 = new Thread(new MyTask(), "t3");

        t1.start();
        t1.join();

        t2.start();
        t2.join();

        t3.start();
        t3.join();
        System.out.println("모든 스레드 실행 완료");

    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                log(i);
                ThreadUtils.sleep(1000);
            }
        }
    }
}
