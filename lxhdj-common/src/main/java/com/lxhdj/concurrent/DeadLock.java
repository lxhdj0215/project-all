package com.lxhdj.concurrent;

import com.lxhdj.constant.Constants;

public class DeadLock {
    private Object lockOne = new Object();
    private Object lockTwo = new Object();


    public void deadLock() {
        Thread threadA = new Thread(() -> {
            synchronized (lockOne) {
                try {
                    Thread.sleep(Constants.CONSTANT_1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockTwo) {
                    System.out.println("thread a");
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (lockTwo) {
                try {
                    Thread.sleep(Constants.CONSTANT_1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockOne) {
                    System.out.println("thread b");
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
