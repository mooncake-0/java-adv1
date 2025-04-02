package thread.sec6_synchronized.sync.problem_solving;

import thread.util.MyLogger;

// 다음 코드의 결과는 20000이 되어야 한다 - Counter 클래스 내부만 수정해서, 문제점을 찾아 해결하라
// 현재 결과: 16345, 12058 등 매번 다름 - 두 쓰레드가 서로 교차하며 진행이 되어서, 누락되는 항목들이 발생한다
public class a_Sol1Problem {

    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    counter.increment();
                }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        MyLogger.log("[결과확인]: " + counter.getCount());
    }

    static class Counter {
        private int count = 0;

        public void increment() {
            count += 1;
        }

        public int getCount() {
            return count;
        }
    }
}
