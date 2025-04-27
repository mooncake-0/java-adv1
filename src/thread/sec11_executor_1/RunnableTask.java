package thread.sec11_executor_1;

import thread.util.MyLogger;
import thread.util.ThreadUtils;

import static thread.util.MyLogger.*;

public class RunnableTask implements Runnable{

    private final String name;
    private int sleepMs = 1000;

    public RunnableTask(String name) {
        this.name = name;
    }

    public RunnableTask(String name, int sleepMs) {
        this.name = name;
        this.sleepMs = sleepMs;
    }

    @Override
    public void run() {
        log(name + " 시작!");
        ThreadUtils.sleep(sleepMs); // 작업하는데 1초 걸리는 작업
        log(name + " 완료!");
    }
}
