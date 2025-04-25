package thread.sec9_cas.spinlock;

import thread.util.ThreadUtils;

import java.util.concurrent.atomic.AtomicBoolean;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.*;

public class MySpinLockTrial {

    //    private volatile boolean lock = false;
    private final AtomicBoolean lock = new AtomicBoolean();

    public void lock() {
        log("락 획득 시도");
        do {
            // 확인해서 없는 상태라면 GET 시도
            sleep(1000); // 확인용
            boolean successful = lock.compareAndSet(false, true);
            if (successful) {
                break;
            } else {
                log("락 획득 실패 - 스핀 대기");
            }
        } while (true);
        log("락 획득 완료 - 함수 종료");
    }

    public void unlock() {
        lock.compareAndSet(true, false);
        log("락 반납 완료");
    }
}
