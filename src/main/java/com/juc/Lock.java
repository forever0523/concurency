package com.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mr.Qian
 * @date 2018/5/20
 * @Description:
 */
public class Lock {
    /**
     * nonFair非公平锁、fair公平锁
     */
    private static ReentrantLock lock = new ReentrantLock(true);

    /**
     * @param
     * @return
     * @throws
     * @Description: 超时test
     * @date 2018/5/20
     */
    private static void tryLockTest() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            lock.lock();
            System.out.println("thread1:" + Thread.currentThread().getId());
            try {
                Thread.sleep(20000);
                System.out.println("thread1:" + Thread.currentThread().getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            try {
                if (lock.tryLock(5, TimeUnit.SECONDS)) {
                    System.out.println("thread2:" + Thread.currentThread().getId());
                    try {
                    } catch (Exception ignored) {
                    } finally {
                        lock.unlock();
                    }
                }
                System.out.println(111);
            } catch (InterruptedException e) {
                System.out.println(222);
                e.printStackTrace();
            }
        });
        thread2.start();
        System.out.println("main:" + Thread.currentThread().getId());
        Thread.sleep(10000);
    }

    /**
     * @Description: interrupted test
     * @param 
     * @return 
     * @throws
     * @date 2018/5/20
     */
    private static void lockInterrupted() {
        Thread thread1 = new Thread(() -> {
            lock.lock();
            int i = 0;
            try {
                Thread.sleep(10000);
                while (i<100000000)
                    i++;
                System.out.println("thread1执行完成。。");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                //lock.lock();
            } catch (InterruptedException e) {
                System.out.println(String.format("thread2:%s", e));
            } finally {
                if (Thread.holdsLock(lock)) {
                    lock.unlock();
                }
            }
        });
        thread2.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        //tryLockTest();
        lockInterrupted();
    }
}
