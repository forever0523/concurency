package com.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mr.Qian
 * @date 2018/5/22
 * @Description:
 */
public class CASDemo {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void runTest() {
        for (int i = 1; i < 3; i++) {
            new Thread(() -> {
                int count = 1;
                while (!atomicInteger.compareAndSet(0, 1)) {
                    System.out.println(String.format("ThreadId:%s,自旋次数:%s", Thread.currentThread().getId(), count));
                    count += 1;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    System.out.println(Thread.currentThread().getId()+"come into..");
                    Thread.sleep(3000);
                    atomicInteger.compareAndSet(1, 0);
                    System.out.println(Thread.currentThread().getId());
                    return;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        runTest();
    }
}

