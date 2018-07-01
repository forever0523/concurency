package com.juc;

/**
 * @desc:
 * @author: qwn48906
 * @date: 2018/5/21
 */
public class ThreadLocalDemo {
    private static ThreadLocal<Product> threadLocal = new ThreadLocal();

    public static void runTest() {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                Product product = new Product(Thread.currentThread().getId());
                threadLocal.set(product);
                System.out.println("set.threadId:" + product.getThreadId() + "." + Thread.currentThread().getId());
                product = threadLocal.get();
                System.out.println("get.threadId:" + product.getThreadId() + "." + Thread.currentThread().getId());
            }).start();
        }
    }

    public static void main(String[] args) {
        runTest();
    }
}
