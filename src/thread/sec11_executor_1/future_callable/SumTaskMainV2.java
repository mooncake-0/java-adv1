package thread.sec11_executor_1.future_callable;

import java.util.concurrent.*;

import static thread.util.MyLogger.log;

public class SumTaskMainV2 {

    // 이전에 했던 부분 - 비교를 위해
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        ExecutorService es = Executors.newFixedThreadPool(2); // 두개 돌려야 한다

        Future<Integer> future1 = es.submit(task1);
        Future<Integer> future2 = es.submit(task2);

        Integer sum1 = future1.get();
        Integer sum2 = future2.get();

        log("task1 + task2 = " + (sum1 + sum2));
        es.close();
    }

    static class SumTask implements Callable<Integer> {

        int startVal;
        int endVal;

        public SumTask(int startVal, int endVal) {
            this.startVal = startVal;
            this.endVal = endVal;
        }

        // 간단한 싱글 스레드 함수처럼 작업 결과를 반환해준다
        @Override
        public Integer call() throws InterruptedException {
            log("작업 시작 - call()");

            Thread.sleep(2000); // 예외를 던질 수 있다!!
            int sum = 0;
            for (int i = startVal; i <= endVal; i++) {
                sum += i;
            }

            log("작업 완료 result = " + sum);
            return sum;
        }
    }

}