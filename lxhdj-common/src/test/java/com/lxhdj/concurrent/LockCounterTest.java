package com.lxhdj.concurrent;

import org.junit.Test;

/**
 * Created by wangguijun1 on 2018/6/14.
 */
public class LockCounterTest {

    @Test
    public void countTest() {
        for (int i = 0; i < 10; i++) {
            LockCounter lockCounter = new LockCounter();
            lockCounter.count();
        }
    }

    @Test
    public void countSynTest() {
        for (int i = 0; i < 10; i++) {
            LockCounter lockCounter = new LockCounter();
            lockCounter.countSyn();
        }
    }
}
