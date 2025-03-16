package thread.sec5_volatile;

import thread.util.MyLogger;
import thread.util.ThreadUtils;

import static thread.util.MyLogger.*;

public class VolatileCountMain {

public static void main(String[] args) {
    MyTask myTask = new MyTask();
    Thread t = new Thread(myTask, "work");
    t.start();
    ThreadUtils.sleep(1000);
    myTask.flag = false;
    log("flag = " + myTask.flag + ", count = " + myTask.count + "의 값으로 종료");
}
static class MyTask implements Runnable {
    volatile boolean flag = true;
    volatile long count;
    @Override
    public void run() {
        while (flag) {
            count++; // 1억번에 한번만 출력
            if (count % 100_000_000 == 0) {
                log("flag = " + flag + ", count = " + count);
            }
        }
        log("flag = " + flag + ", count = " + count + "의 값으로 종료");
    }
}
}
