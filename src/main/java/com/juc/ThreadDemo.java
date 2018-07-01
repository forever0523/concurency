package com.juc;

/**
 * @desc:
 * @author: qwn48906
 * @date: 2018/5/18
 */
public class ThreadDemo extends Thread{
	static ThreadLocal<Integer> tl = new ThreadLocal<>();
	static ThreadLocal<String> localString = new ThreadLocal<>();
	public static void main(String[] args) {

		for (int i = 0; i < 5; i++) {
			new Thread(new ThreadLocalThread()).start();
		}

		//Lock lock = new ReadWriteLock();
	}


	public static class ThreadLocalThread implements Runnable{

		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			localString.set(name);
			//System.out.println(name);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("线程"+ name + ":" + localString.get());
		}
	}
}
