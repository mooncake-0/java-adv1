package thread.sec3_start.runnable_만드는법;

import static thread.util.MyLogger.log;

// Lambda 식을 활용해서 Runnable 을 바로 정의, 하지만 재사용은 어려움
public class InnerRunnableMainV3 {

    public static void main(String[] args) {

        log(":main() START!");

        Thread thread = new Thread(() -> log(":run() START & END!"));
        thread.start();

        log(":main() END!");
    }

}
