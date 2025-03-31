package thread.sec6_synchronized.sync;

import thread.util.ThreadUtils;

import static thread.util.MyLogger.log;

// 임계 영역이 너무 크기 때문에, 코드 블럭으로 임계 영역을 정의한다. -> 성능을 챙긴다
public class BankAccountV3 implements BankAccount {

    private int balance;

    public BankAccountV3(int initialBalance) { // 생성할 때 얼마 있는지 넣어줄 예정
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) { // sync 키워드가 붙으면 이 함수는 하나의 스레드만 접근된다

        log("거래 시작: " + getClass().getSimpleName());

        synchronized (this) {
            // 잔고가 출금액보다 적으면 실패!
            if (amount > balance) {
                log("[실패] - 계좌 잔액이 부족합니다");
                return false;
            }

            // 잔고가 출금액보다 많으면 진행
            log("[가능] - 출금액: " + amount + ", 잔액: " + balance);
            ThreadUtils.sleep(1000); // 출금에 걸리는 시간으로 가정
            this.balance = this.balance - amount;
            log("[출금완료] - 출금액: " + amount + ", 잔액: " + balance);
        }
        log("거래 종료: " + getClass().getSimpleName());
        return true;
    }

    @Override
    public synchronized int getBalance() {
        return this.balance;
    } // 읽는 부분에도 묶어준다
}
