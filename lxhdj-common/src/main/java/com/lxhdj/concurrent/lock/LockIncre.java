package com.lxhdj.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

public class LockIncre {

	private static ReentrantLock lock = new ReentrantLock();
	private static int count = 0;

	public static void main(String[] args) throws InterruptedException {
		int num = 2;
		Thread[] threads = new Thread[num];
		for (int i = 0; i < num; i++) {
			threads[i] = new TestThread();
			threads[i].start();
		}
		for (int i = 0; i < num; i++) {
			threads[i].join();
		}
		System.out.println(count);

	}

	public static void incre() {
		try {
			lock.lock();
			Thread.sleep(1000 * 60);
			count++;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	static class TestThread extends Thread {
		public void run() {
			incre();
			try {
				Thread.sleep((long) (Math.random() * 10));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
