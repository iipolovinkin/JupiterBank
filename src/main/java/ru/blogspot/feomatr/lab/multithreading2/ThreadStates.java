package ru.blogspot.feomatr.lab.multithreading2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class ThreadStates {
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread();
        System.out.println("thread1.getState() = " + thread1.getState());
        thread1.start();
        thread1.setUncaughtExceptionHandler(null);
        System.out.println("thread1.getState() = " + thread1.getState());
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();
        Thread.sleep(50L);
        Thread thread3 = new Thread(() -> System.out.println("hello"));
        thread3.start();
        Thread.sleep(100L);
        Thread thread4 = new Thread(() -> {
            //                Thread.currentThread().wait(100);
            LockSupport.park(20_0);
        });
        thread4.start();

        new Thread(() -> {
            System.out.println("thread2.getState() = " + thread2.getState());
            System.out.println("thread4.getState() = " + thread4.getState());
            System.out.println("unpark thread4");
            try {
                LockSupport.unpark(thread4);
                Thread.sleep(100);
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("thread4.getState() = " + thread4.getState());
        }).start();
        System.out.println("thread3.getState() = " + thread3.getState());
//        System.out.println("thread2.getState() = " + thread2.getState());

        thread1.wait(200);


    }
}
