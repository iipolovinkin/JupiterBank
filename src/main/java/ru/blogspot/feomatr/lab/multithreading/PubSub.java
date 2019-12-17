package ru.blogspot.feomatr.lab.multithreading;

public class PubSub {
    private String message;

    public static void main(String[] args) throws InterruptedException {
        final PubSub pubSub = new PubSub();

        new Thread(new Runnable() {
            @Override
            public void run() {
                pubSub.doMessages(1);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                pubSub.doMessages(2);
            }
        }).start();
        Thread.sleep(1_00);
        new Thread(new Runnable() {
            @Override
            public void run() {
                pubSub.sendMessage("HELLO1");
            }
        }).start();

    }

    public void doMessages(int i) {
        try {
            synchronized (this) {
                while (message == null) {
                    System.out.println("wait " + i);
                    wait(1000);
                    System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
                    System.out.println("wait2 " + i);
                }
                System.out.println("Recv: " + message);
            }
        } catch (InterruptedException e) {
            return;
        }
    }

    public void sendMessage(String message) {
        synchronized (this) {
            this.message = message;
            notify();
        }
    }

}
