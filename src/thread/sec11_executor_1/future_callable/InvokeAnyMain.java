package thread.sec11_executor_1.future_callable;

import thread.sec11_executor_1.CallableTask;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static thread.util.MyLogger.log;

public class InvokeAnyMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(10);

        CallableTask task1 = new CallableTask("task1", 1000);
        CallableTask task2 = new CallableTask("task2", 2000);
        CallableTask task3 = new CallableTask("task3", 3000);

        List<CallableTask> tasks = List.of(task1, task2, task3);

        // 다 취소시키지만, Interrupt 를 거는 행위이기 때문에 Interrupt 체킹이 안되어 있으면 딱히 잡진 않는다
        // 하지만 작업 결과는 받아올 수 없다. 그래서 Future 를 제공해주지 않는다
        Integer firstFinishedResult = es.invokeAny(tasks);
        log("(firstFinished) value = " + firstFinishedResult);
        es.close();
    }
}
