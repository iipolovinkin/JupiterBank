package ru.blogspot.feomatr.lab.concurrent;

import java.util.concurrent.*;

public class FutureDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future f = executorService.submit(()-> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("args = " + args);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        try {
            System.out.println("f.isDone() = " + f.isDone());
            System.out.println("f.isCancelled() = " + f.isCancelled());
            System.out.println("f.cancel() = " + f.cancel(true));
            System.out.println("f.get() = " + f.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }
}
