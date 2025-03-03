package thread.sec3_start;


// 보조적인 일을 하는 스레드가 필요할 때 사용, 정확한 기준은 모호
public class DaemonThreadMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main () START!");

        DaemonThread dThread = new DaemonThread();
        dThread.setDaemon(true); // 해당 스레드는 데몬 스레드
        dThread.start();

        System.out.println(Thread.currentThread().getName() + ": main () END!");
    }

    static class DaemonThread extends Thread {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": running...");

            try {
                Thread.sleep(1000); // 10 초간 스레드 대기
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("THREAD NEEDS TO PRINT THIS!");
            System.out.println(Thread.currentThread().getName() + ": END!");
        }
    }
}
