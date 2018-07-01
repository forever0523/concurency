package com.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

/**
 * @author Mr.Qian
 * @date 2018/5/20
 * @Description:
 */
public class ConditionDemo {
    private static Lock lock = new ReentrantLock();
    private static Condition readCondition = lock.newCondition();
    private static Condition writeCondition = lock.newCondition();
    private static class CustomThread extends Thread {
        public CustomThread(String threadName, Runnable runnable) {
            super(runnable);
            this.setName(threadName);
        }
    }
    public static void runTest() throws ExecutionException, InterruptedException, BrokenBarrierException {
        for (int i = 0; i < 10; i++) {
            CustomThread thread1 = new CustomThread(String.valueOf(i), () -> {
                try {
                    lock.lock();
                    readCondition.await();
                    System.out.println(String.format("读线程:%s 唤醒", Thread.currentThread().getName()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
            thread1.start();
            CustomThread thread2 = new CustomThread(String.valueOf(i), () -> {
                try {
                    lock.lock();
                    writeCondition.await();
                    System.out.println(String.format("写线程:%s 唤醒", Thread.currentThread().getName()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
            thread2.start();
        }
        Thread.sleep(3000);
        lock.lock();
        readCondition.signalAll();
        lock.unlock();
        Thread.sleep(3000);
        lock.lock();
        writeCondition.signalAll();
        lock.unlock();
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException, BrokenBarrierException {
        runTest();
    }
}