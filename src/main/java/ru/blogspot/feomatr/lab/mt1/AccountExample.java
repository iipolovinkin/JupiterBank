package ru.blogspot.feomatr.lab.mt1;

import java.util.concurrent.TimeUnit;

public class AccountExample implements Runnable {
    private Account acct = new Account();

    public static void main(String[] args) throws InterruptedException {
        AccountExample accountExample = new AccountExample();
        accountExample.runThreads();
    }

    private void runThreads() throws InterruptedException {
        Thread thread1 = new Thread(() -> run());
        Thread thread2 = new Thread(() -> run());
        thread1.start();
        thread2.start();

        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("acct = " + acct);
    }

    private void makeWithdrawal(int amt) {
        if (acct.getBalance() >= amt) {
            System.out.printf("Thread: %s, balance: %s \n", Thread.currentThread().getName(), acct.getBalance());
            acct.withdraw(amt);
        }
    }

    public void run() {
        for (int x = 0; x < 5; x++) {
            makeWithdrawal(10);
            if (acct.getBalance() < 0) {
                System.out.println("account is overdrawn!");
            }
        }
    }

}
