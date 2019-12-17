package ru.blogspot.feomatr.lab.concurrent;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(1, true);
        RejectedExecutionHandler handler = (r, executor) -> System.out.println("r = " + r);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 8, 1,
                TimeUnit.SECONDS, workQueue, handler);

        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.submit(getRunnable(i));
        }

        threadPoolExecutor.shutdown();
    }

    private static Runnable getRunnable(final int finalI) {
        return () -> {
            try {
                System.out.println("i = " + finalI);
                sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
}
