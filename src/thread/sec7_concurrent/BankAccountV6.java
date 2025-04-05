package thread.sec7_concurrent;

import thread.sec6_synchronized.sync.BankAccount;
import thread.util.ThreadUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static thread.util.MyLogger.log;

// V5: tryLock - WAITING 상태 없음. 결과만 바로 반환
// V6: tryLock(sec) - 정해진 시간만큼만 TIMED_WAITING. 그 와중에 interrupt 가능 -> 그래도 어느 정도는 기다려봐라.. 하는 것
//
public class BankAccountV6 implements BankAccount {

    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV6(int initialBalance) { // 생성할 때 얼마 있는지 넣어줄 예정
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {

        log("거래 시작: " + getClass().getSimpleName());

        try {
            if (!lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                log("[진입 실패]: 이미 락을 누가 사용중입니다");
                return false;
            }
        } catch (InterruptedException exception) {
            log("뒤늦게 온 스레드가 그래도 잠깐 대기를 해보는 와중에 Interrupted 가 발생하여 RUNNABLE 로 변경");
            throw new RuntimeException(exception);
        }

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
    }
}
