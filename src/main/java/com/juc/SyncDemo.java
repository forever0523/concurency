package com.juc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Qian
 * @Description:
 * @date 2018/5/19
 */
public class SyncDemo {

    public synchronized void method() {
        System.out.println("锁住当前对象实例...");
    }

    public synchronized static void staticMethod() {
        System.out.println("锁住class");
    }

    private Object lock = new Object();
    public void method1() {
        synchronized (lock) {
            System.out.println("锁住代码块");
        }
    }

    public static void main(String[] args) {
        SyncDemo demo = new SyncDemo();
        demo.method1();
        System.out.println(String.format("t1:%s running over", Thread.currentThread().getId()));
        Map m = Collections.synchronizedMap(new HashMap());

    }
}
