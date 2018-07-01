package com.juc;

import java.util.concurrent.*;

public class CyclicBarrierDemo {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(6);

    public  static void runTest() throws InterruptedException {
        for (int i = 0; i < 12; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getId() + "await.");
                    cyclicBarrier.await();
                    //Thread.sleep(finalI * 500);
                    System.out.println(Thread.currentThread().getId() + "go.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        runTest();
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(1, 1, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(1),
                        new ThreadPoolExecutor.DiscardOldestPolicy());
        threadPoolExecutor.execute(new Thread());
        ExecutorService fiexdService = Executors.newFixedThreadPool(2);
        Executors.newScheduledThreadPool(1);
        ExecutorService cachedServic = Executors.newCachedThreadPool();
        ((ThreadPoolExecutor)fiexdService).setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        fiexdService.execute(new Thread());
    }
}
