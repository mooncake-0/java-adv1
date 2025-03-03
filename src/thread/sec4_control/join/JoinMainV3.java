package thread.sec4_control.join;

import thread.util.ThreadUtils;

import static thread.util.MyLogger.log;

public class JoinMainV3 {

    // 직접 이 스레드의 작업을 기다리라고 말하는 방법 - Join()
    public static void main(String[] args) throws InterruptedException {
        log("Start");

        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        // 메인 스레드는 타 스레드들이 종료될 때까지 대기 (해당 라인을 수행하는 스레드는 join 을 사용하는 스레드가 완료될 때까지 대기해야 한다)
        log("join() - main 스레드가 t1, t2 종료까지 대기");
        thread1.join();
        thread2.join();
        log("main 스레드 대기 종료");

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
