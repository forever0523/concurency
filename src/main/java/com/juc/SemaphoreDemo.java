package com.juc;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    static Semaphore semaphore = new Semaphore(3);
    public static void runTest() throws ExecutionException, InterruptedException {

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getId() + " start at " + new Date());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        runTest();
    }
}
