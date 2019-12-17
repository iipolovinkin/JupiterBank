package ru.blogspot.feomatr.lab.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo1 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 10_000, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));

        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.submit(createRunnable(i));
        }

        threadPoolExecutor.shutdown();
    }

    private static Runnable createRunnable(final Integer num) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("finish num = " + num);
            }
        };
    }
}
