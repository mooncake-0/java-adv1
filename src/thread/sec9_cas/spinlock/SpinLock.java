package thread.sec9_cas.spinlock;

import thread.util.MyLogger;

import java.util.concurrent.atomic.AtomicBoolean;

import static thread.util.MyLogger.*;

public class SpinLock {

    private final AtomicBoolean lock = new AtomicBoolean();

    public void lock() {
        log("락 획득 시도 ");

        // CAS 연산이 성공할때까지 (실패하면 계속 돈다)
        // ㅋ.. 훨씬 깔끔
        while (!lock.compareAndSet(false, true)) { // get 을 통한 확인 생략 (이미 포함되어 있다)
            // 락을 획득할 때까지 Spin 대기 (스레드 = RUNNABLE 상태로 대기)
            log("락 획득 실패 - 스핀 대기");
        }

        log("락 획득 완료 - 함수 종료");
    }

    public void unlock() {
        lock.set(false); // lock = false 는 값 대입이므로 (확인 필요 없음) 원자적이다. 따라서 단순 set 활용한다
        log("락 반납 완료");
    }
}
