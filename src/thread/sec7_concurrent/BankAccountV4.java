package thread.sec7_concurrent;

import thread.sec6_synchronized.sync.BankAccount;
import thread.util.ThreadUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static thread.util.MyLogger.log;

// sync 블록을 쓰지 않고 문제를 해결해보자
public class BankAccountV4 implements BankAccount {

    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV4(int initialBalance) { // 생성할 때 얼마 있는지 넣어줄 예정
        this.balance = initialBalance;
    }
    @Override
    public boolean withdraw(int amount) { // sync 키워드가 붙으면 이 함수는 하나의 스레드만 접근된다

        log("거래 시작: " + getClass().getSimpleName());

        lock.lock(); // 앞으로 올 임계 영역을 실행함에 있어서, 먼저 온 스레드가 lock 을 획득한다 ==================
        try {
            if (amount > balance) {
                log("[실패] - 계좌 잔액이 부족합니다");
                return false;
            }

            log("[가능] - 출금액: " + amount + ", 잔액: " + balance);
            ThreadUtils.sleep(1000); // 출금에 걸리는 시간으로 가정
            this.balance = this.balance - amount;
            log("[출금완료] - 출금액: " + amount + ", 잔액: " + balance);

            // lock.unlock(); // 임계영역이 끝나는 구간, 락을 내려 놓으면 위에서 대기중인 스레드가 임계영역을 시작한다 =========

            // 참고로 lock 을 사용했으면, 100% 무조건 unlock 을 해줘야 한다 안그러면 무한 WAITING 가능
            // 지금 위에서 return false 하면 unlock 코드 안돈다 -> 따라서 unlock 은 finally 습관!!
            // 이 부분 이후에 try 들어온 것
        }finally {
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
