package thread.sec3_start;

public class HelloThreadMain {

    public static void main(String[] args) {
        Thread whoIsRunningMain = Thread.currentThread();
        System.out.println(whoIsRunningMain.getName() + ": main() START!");

        HelloThread myThread = new HelloThread();

        System.out.println(Thread.currentThread().getName() + ": sec3_start 호출 전 Thread");
        myThread.start(); // 내가 만든 Thread 를 sec3_start 한다 (run 함수를 사용하는게 아니다)
        System.out.println(Thread.currentThread().getName() + ": sec3_start 호출 후 Thread");

        System.out.println(whoIsRunningMain.getName() + ": main() END!");
    }

}
