package com.lxhdj.concurrent.deadlock;

public class DeadLock {
	private Object lockOne = new Object();
	private Object lockTwo = new Object();

	public static void main(String[] args) {
		DeadLock deadLock = new DeadLock();
		deadLock.deadLock();
	}

	public void deadLock() {
		Thread threadA = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (lockOne) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (lockTwo) {
						System.out.println("");
					}
				}
			}
		});

		Thread threadB = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (lockTwo) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized (lockOne) {
						System.out.println("");
					}
				}
			}
		});

		threadA.start();
		threadB.start();
	}
}
