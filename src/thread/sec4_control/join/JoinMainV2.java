package thread.sec4_control.join;

import thread.util.ThreadUtils;

import static thread.util.MyLogger.log;

public class JoinMainV2 {

    // 가장 기본적으로 스레드를 기다리게 하는 방법 - Sleep(ms)
    public static void main(String[] args) {
        log("Start");

        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        // 가장 기본적인 기다리게 하는 방법 - 딱봐도 정확한 타이밍을 맞춰서 기다리긴 어려워 보임
        log("main 스레드 sleep");
        ThreadUtils.sleep(3000);
        log("main 스레드 깨어남");

        log("task1.result = " + task1.result);
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
