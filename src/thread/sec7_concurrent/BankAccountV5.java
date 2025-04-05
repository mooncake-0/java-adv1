package thread.sec7_concurrent;

import thread.sec6_synchronized.sync.BankAccount;
import thread.util.ThreadUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static thread.util.MyLogger.log;

// tryLock - WAITING 상태 없음. 결과만 바로 반환
// tryLock(sec) - 정해진 시간만큼만 TIMED_WAITING. 그 와중에 interrupt 가능
public class BankAccountV5 implements BankAccount {

    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV5(int initialBalance) { // 생성할 때 얼마 있는지 넣어줄 예정
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {

        log("거래 시작: " + getClass().getSimpleName());

        // 락을 얻을 수 있으면 얻고, 얻지 못하면 그냥 실패처리 해야 할때 (안기다려야 할 때) - 이걸 썼어야 했다
        if (!lock.tryLock()) { // tryLock 은 얻을 수 있으면 얻는 행위도 포함되어 있다
            log("[진입 실패]: 이미 락을 누가 사용중입니다");
            return false;
        }

        // tryLock 에 lock 얻으려는 작업이 포함되어 있기 때문
        // lock.lock();
        try {
            if (amount > balance) {
                log("[실패] - 계좌 잔액이 부족합니다");
                return false;
            }

            log("[가능] - 출금액: " + amount + ", 잔액: " + balance);
            ThreadUtils.sleep(1000); // 출금에 걸리는 시간으로 가정
            this.balance = this.balance - amount;
            log("[출금완료] - 출금액: " + amount + ", 잔액: " + balance);

        } finally {
            lock.unlock();
        }

        log("거래 종료: " + getClass().getSimpleName());
        return true;
    }


    @Override
    public synchronized int getBalance() {
        return this.balance;
    } // 읽는 부분에도 묶어준다
}
