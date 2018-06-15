package com.lxhdj.concurrent;

import org.junit.Test;

/**
 * Created by wangguijun1 on 2018/6/14.
 */
public class DeadLockTest {

    @Test
    public void deadLockTest() {
        DeadLock deadLock = new DeadLock();
        deadLock.deadLock();
    }
}
