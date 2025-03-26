package thread.sec6_synchronized.sync;

import thread.util.MyLogger;
import thread.util.ThreadUtils;

import static thread.util.MyLogger.*;

public class BankMain {

    public static void main(String[] args) throws InterruptedException {

        BankAccount account = new BankAccountV1(1000);

        // 악의적인 유저의 동시 출금 시도
        Thread t1 = new Thread(new WithdrawTask(account, 800), "w-t1");
        Thread t2 = new Thread(new WithdrawTask(account, 800), "w-t2");

        t1.start(); // 800 원 출금
        t2.start();

        ThreadUtils.sleep(500);
        log("t1 state: " + t1.getState());
        log("t2 state: " + t2.getState());

        t1.join(); // 출금 완료 대기
        t2.join();

        log("최종 잔액: " + account.getBalance());

    }
}
