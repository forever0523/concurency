package com.juc;

import java.util.concurrent.TimeUnit;

/**
 * @author Mr.Qian
 * @date 2018/5/20
 * @Description:
 */
public class MyThread implements Runnable  {

    //@Override
    public void run() {
        try {
            doWork();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void doWork() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable myThread = new MyThread();
        for (int i = 1; i <= 10; i ++) {
            TimeUnit.SECONDS.sleep(1);
            Thread t = new Thread(myThread,"线程编号-" + i);
            t.start();
        }
    }

}