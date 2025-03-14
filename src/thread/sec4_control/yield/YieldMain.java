package thread.sec4_control.yield;

import thread.util.ThreadUtils;

public class YieldMain {
    static final int THREAD_CNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_CNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
                // 아무것도 안했을 경우, Random 이긴 하지만, 그래도 한 스레드가 쭈욱 실행되는 느낌이 들긴 함 222444555666667711 막 이런 느낌
                // ThreadUtils.sleep(1); // Sleep 을 할경우 Thread 상태가 바뀌기 때문에, 쭈욱 실행드는 느낌이 거의 없고 완전히 Random 하게 실행된다 94 -> 734 -> 233 -> 342 이런 느낌
//            Thread.yield(); // RUNNABLE 상태로 한 TURN 기다리는 것 -> 아무것도 안한 것과, Sleep 의 사이 정도의 느낌을 받을 수 있다 (그냥 그렇다고 해라!)
            }
        }
    }
}
