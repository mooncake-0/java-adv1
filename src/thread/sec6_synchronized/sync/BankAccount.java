package thread.sec6_synchronized.sync;

public interface BankAccount {
    boolean withdraw(int amount); // 출금할 금액
    int getBalance(); // 계좌 잔액 반환
}
