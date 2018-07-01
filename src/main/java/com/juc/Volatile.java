package com.juc;

/**
 * @author Mr.Qian
 * @date 2018/5/21
 * @Description:
 */
public class Volatile {
    static  boolean stop = false;
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            int count = 0;
            @Override
            public void run() {
                while(!stop){
                    System.out.println("........");
                   count ++;
                }
                System.out.println(count);
            }
        });
        Thread t2 = new Thread(() -> stop = true);
        t1.start();
        t2.start();
    }
}
