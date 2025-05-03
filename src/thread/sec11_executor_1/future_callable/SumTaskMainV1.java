package thread.sec11_executor_1.future_callable;

import static thread.util.MyLogger.log;

public class SumTaskMainV1 {

    // 이전에 했던 부분 - 비교를 위해
    public static void main(String[] args) throws InterruptedException {

        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);

        Thread th1 = new Thread(task1, "th1");
        Thread th2 = new Thread(task2, "th2");
        th1.start();
        th2.start();

        log("main 스레드는 th1, th2 작업이 끝날때가지 대기");
        th1.join();
        th2.join();
        log("main 스레드는 대기 완료");

        int sum = task1.result + task2.result;
        log("sum result = " + sum);
    }

    static class SumTask implements Runnable {

        int startVal;
        int endVal;
        int result; // 작업 결과 저장소

        public SumTask(int startVal, int endVal) {
            this.startVal = startVal;
            this.endVal = endVal;
        }

        @Override
        public void run() {

            log("run : 작업 시작");

            try {
                Thread.sleep(2000); // 예외를 던질 수 없다
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int sum = 0;
            for (int i = startVal; i <= endVal; i++) {
                sum += i;
            }

            result = sum;
            log("run : 작업 시작");
        }
    }

}
