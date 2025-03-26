package thread.sec6_synchronized.sync;

// 출금을 전담하는 Thread Work
public class WithdrawTask implements Runnable{

    private BankAccount account;
    private int amount;

    public WithdrawTask(BankAccount account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        account.withdraw(amount);
    }
}
