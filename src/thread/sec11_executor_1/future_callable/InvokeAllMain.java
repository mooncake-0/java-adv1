package thread.sec11_executor_1.future_callable;

import thread.sec11_executor_1.CallableTask;
import thread.util.MyLogger;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static thread.util.MyLogger.*;

public class InvokeAllMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        CallableTask task1 = new CallableTask("task1", 1000);
        CallableTask task2 = new CallableTask("task2", 2000);
        CallableTask task3 = new CallableTask("task3", 3000);

        List<CallableTask> tasks = List.of(task1, task2, task3);

        // 참고로 invokeAll 역시 InterruptedException 을 던지는데, 이는 "바로 대기" 하기 때문이다
        // 앞서 살펴본 submit 으로 하나씩 작업을 전달하는 형태가 아니기 때문에, 바로 전체 작업을 던진 뒤 대기를 해도 되는 것이다!
        // Q)) 그럼 얘는 왜 굳이 Future 를 썼을까? 그냥 Class 의 동작성 일치 때문인 것 같다
        List<Future<Integer>> futures = es.invokeAll(tasks);
        for (Future<Integer> future : futures) {
            Integer val = future.get();
            log("value = " + val);
        }
        es.close();
    }
}
