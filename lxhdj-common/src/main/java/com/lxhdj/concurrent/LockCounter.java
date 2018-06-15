package com.lxhdj.concurrent;

import com.lxhdj.constant.Constants;

import java.util.concurrent.locks.ReentrantLock;

public class LockCounter {
    private ReentrantLock lock = new ReentrantLock();
    private int count = 0;

    public void count() {
        int num = Constants.CONSTANT_1000;
        Thread[] threads = new Thread[num];
        for (int i = 0; i < num; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < Constants.CONSTANT_1000; j++) {
                    incre();
                }
            });
            threads[i].start();
        }
        for (int i = 0; i < num; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(count);

    }

    public void countSyn() {
        int num = Constants.CONSTANT_1000;
        Thread[] threads = new Thread[num];
        for (int i = 0; i < num; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < Constants.CONSTANT_1000; j++) {
                    synIncre();
                }
            });
            threads[i].start();
        }
        for (int i = 0; i < num; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(count);

    }

    public void incre() {
        count++;
    }

    public void synIncre() {
        try {
            lock.lock();
            count++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
