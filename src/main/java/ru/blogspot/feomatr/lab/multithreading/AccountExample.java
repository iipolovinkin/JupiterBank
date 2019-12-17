package ru.blogspot.feomatr.lab.multithreading;

public class AccountExample implements Runnable {
    private Account acct = new Account();

    public static void main(String[] args) {
        new AccountExample().runThreads();
    }

    private void runThreads() {
        new Thread(this).start();
        new Thread(this).start();
    }

    private synchronized void makeWithdrawal(int amt) {
        if (acct.getBalance() >= amt) {
            System.out.println("balance = " + acct.getBalance());
            acct.withdraw(amt);
        }
    }

    @Override
    public void run() {
        for (int x = 0; x < 5; x++) {
            makeWithdrawal(10);
            if (acct.getBalance() < 0) {
                System.out.println("account is overdrawn! " + acct.getBalance());
            }
        }
    }
}