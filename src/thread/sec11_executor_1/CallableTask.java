package thread.sec11_executor_1;

import thread.util.MyLogger;
import thread.util.ThreadUtils;

import java.util.concurrent.Callable;

import static thread.util.MyLogger.*;

// 섹션 마지막 Collection 으로 넣는거 할 때 사용됨
public class CallableTask implements Callable<Integer> {

    private String name;
    private int sleepMs = 1000;

    public CallableTask(String name) {
        this.name = name;
    }

    public CallableTask(String name, int sleepMs) {
        this.name = name;
        this.sleepMs = sleepMs;
    }

    @Override
    public Integer call() throws Exception {

        log(name + " 작업 실행");
        ThreadUtils.sleep(1000); // 작업 진행
        log(name + " 작업 완료");
        return sleepMs; // 의미 없는 반환
    }
}
