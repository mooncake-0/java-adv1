package thread.sec3_start.예제풀이;


import static thread.util.MyLogger.*;

/*
문제 4: 여러 스레드 사용
`Thread-A` , `Thread-B` 두 스레드를 만들어라
`Thread-A` 는 1초에 한 번씩 "A"를 출력한다.
`Thread-B` 는 0.5초에 한 번씩 "B"를 출력한다. 이 프로그램은 강제 종료할 때 까지 계속 실행된다.
*/
public class Sol3 {

    public static void main(String[] args) {

        // 각 Runnable 을 생성해도 되지만, Runnable 역시 단순히 Class 임을 확인하고, 유사 작업이기 때문에 동일 클래스를 활용해본다
//        Thread th1 = new Thread(new ARunnable());
//        Thread th2 = new Thread(new BRunnable());

        Thread th1 = new Thread(new SamePrintTaskRunnable(1000, "A"));
        Thread th2 = new Thread(new SamePrintTaskRunnable(500, "B"));

        th1.start();
        th2.start();

    }

    static class SamePrintTaskRunnable implements Runnable {

        private int sleepMs;
        private String content;

        public SamePrintTaskRunnable(int sleepMs, String content) {
            this.sleepMs = sleepMs;
            this.content = content;
        }

        @Override
        public void run() {
            while (true) { // 무한 루프 log(content);
                log(content);
                try {
                    Thread.sleep(sleepMs);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
