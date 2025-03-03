package thread.sec3_start;

public class HelloRunnableMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() START!");

        HelloRunnable helloRunnable = new HelloRunnable();
        Thread thr = new Thread(helloRunnable);
        thr.start();

        System.out.println(Thread.currentThread().getName() + ": main() END!");
    }
}
