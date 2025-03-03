package thread.sec4_control.join;

import thread.util.ThreadUtils;

import static thread.util.MyLogger.log;

public class JoinMainV4 {

    // 무기한이 아니고 기다리는 제한 시간을 걸어주고 완료를 기다리는 방법 - Join(ms)
    // 간단하게 확인하기 위해 T2 는 삭제, T1 의 결과만 확인하도록
    public static void main(String[] args) throws InterruptedException {
        log("Start");

        SumTask task1 = new SumTask(1, 50);
        Thread thread1 = new Thread(task1, "thread-1");

        thread1.start();

        log("join(1000) - main 스레드가 t1, t2 종료까지 1초만 대기");
        thread1.join(1000);
        log("main 스레드 대기 종료");

        log("task1.result = " + task1.result);
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
