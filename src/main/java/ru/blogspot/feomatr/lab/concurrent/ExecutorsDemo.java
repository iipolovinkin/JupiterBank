package ru.blogspot.feomatr.lab.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsDemo {
    public static void main(String[] args) {

        runTasks(Executors.newSingleThreadExecutor(), 10, 500, 0);
//        runTasks(Executors.newFixedThreadPool(4), 10, 500, 0);
//        runTasks(Executors.newCachedThreadPool(), 10, 500, 0);
//        runTasks(Executors.newSingleThreadScheduledExecutor(), 10, 500, 0);
//        runTasks(Executors.newScheduledThreadPool(4), 10, 500, 0);

    }

    private static void runTasks(ExecutorService executorService, int threadCount, long millis, int add) {
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(getRunnable(millis + i * add, i));
        }
        executorService.shutdown();
    }

    private static Runnable getRunnable(long timeOut, int num) {
        return () -> {
            try {
                System.out.printf("Num: %s, timeout: %s, Thread :%s \n", num, timeOut, Thread.currentThread().getName());
                Thread.sleep(timeOut);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
}
