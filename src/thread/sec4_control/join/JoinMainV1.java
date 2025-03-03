package thread.sec4_control.join;

import thread.util.ThreadUtils;

import static thread.util.MyLogger.log;

public class JoinMainV1 {

    public static void main(String[] args) { // Client 는 두 스레드의 합을 사용해야 한다
        log("Start");

        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        log("task1.result = " + task1.result); // 각 스레드들이 2초간 대기먼저 하므로 다 0으로 찍힌다
        log("task2.result = " + task2.result);

        int sumAll = task1.result + task2.result;
        log("total result = " + sumAll);

        log("End");
    }

    static class SumTask implements Runnable {

        // private 해도 중첩클래스니까 클래스 내에선 접근 가능
        private int startValue;
        private int endValue;
        private int result;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() { // 2초짜리의 작업을 하는 스레드라고 가정
            log("작업 시작");
            ThreadUtils.sleep(2000);
            for (int i = startValue; i <= endValue; i++) {
                result += i;
            }
            log("작업 완료, result = " + this.result);
        }
    }
}
