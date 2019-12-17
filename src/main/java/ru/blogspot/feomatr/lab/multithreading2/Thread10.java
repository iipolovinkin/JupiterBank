package ru.blogspot.feomatr.lab.multithreading2;

import static java.lang.String.format;

public class Thread10 {
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 4; ++i) {
            new Thread(new SomeTask()).start();
        }
        System.out.println("Waiting end of some task.");

    }

    private static class SomeTask implements Runnable {
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.print(format("#%d(%s)", i,
                        Thread.currentThread().getName()));
            }
            System.out.println();
        }
    }
}
