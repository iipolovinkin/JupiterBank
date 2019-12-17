package ru.blogspot.feomatr.multithreading;

class Account {
    int balance = 50;

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        balance = balance - amount;
    }
}

