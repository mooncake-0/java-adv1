package thread.sec3_start.runnable_만드는법;

import static thread.util.MyLogger.*;

// 익명 클래스를 활용해서 정의
public class InnerRunnableMainV2 {

    public static void main(String[] args) {

        log(":main() START!");

        Runnable anonRun = new Runnable() {
            @Override
            public void run() {
                log(":run() START & END!");
            }
        };

        Thread thread = new Thread(anonRun);
        thread.start();

        log(":main() END!");
    }

}
