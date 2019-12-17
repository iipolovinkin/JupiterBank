package ru.blogspot.feomatr.lab.mt1;

class Account {
    private int balance = 50;

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        balance = balance - amount;
    }

    @Override
    public String toString() {
        return "balance " + balance;
    }
}

