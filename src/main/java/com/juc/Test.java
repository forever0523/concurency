package com.juc;

/**
 * @author Mr.Qian
 * @date 2018/5/22
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("20000");
            }
        });
        thread.start();
        System.out.println("main..");
    }
}
