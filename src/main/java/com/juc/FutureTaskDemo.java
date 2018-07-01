package com.juc;

import java.util.concurrent.*;

/**
 * @author Mr.Qian
 * @date 2018/5/22
 * @Description:
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        Future<Product> future = threadPool.submit(new Callable<Product>() {
            @Override
            public Product call() throws Exception {
                System.out.println("come into future");
                Thread.sleep(2000);
                System.out.println("2000over");
                return new Product(Thread.currentThread().getId());
            }
        });

        FutureTask<Product> futureTask = new FutureTask<>(new Callable<Product>() {
            @Override
            public Product call() throws Exception {
                System.out.println("come into future");
                Thread.sleep(2000);
                System.out.println("2000over");
                return new Product(Thread.currentThread().getId());
            }
        });
        futureTask .run();
        System.out.println(futureTask.cancel(true));
        //Thread.sleep(1000);
        //System.out.println(future.cancel(true));
        try {
            //System.out.println(future.get());
            System.out.println(futureTask.get().getThreadId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
