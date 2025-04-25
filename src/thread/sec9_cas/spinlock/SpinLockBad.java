package thread.sec9_cas.spinlock;

import thread.util.MyLogger;
import thread.util.ThreadUtils;

import static thread.util.MyLogger.*;
import static thread.util.ThreadUtils.*;

// * Spin Lock - 스레드를 대기시키지 않으면서 락을 만드는 기법
public class SpinLockBad {

    private volatile boolean lock = false;

    public void lock() {
        log("락 획득 시도 ");
        while (true) {
            if (!lock) { // 1 - 공용 자원 LOCK 이 사용중이지 않다면
                sleep(1000); // 스레드 중첩을 위함, 단순 확인용
                lock = true; // 2 - LOCK 을 사용중이도록 변경
                break;
            } else {
                // 락을 획득할 때까지 Spin 대기 (스레드 = RUNNABLE 상태로 대기)
                // WHILE 문을 계속 돈다
                log("락 획득 실패 - 스핀 대기");
            }
        }
        log("락 획득 완료 - 함수 종료");
    }

    public void unlock() {
        lock = false;
        log("락 반납 완료");
    }
}
