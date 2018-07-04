package com.lxhdj.util;

import javassist.bytecode.ConstPool;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by wangguijun1 on 2018/6/15.
 */
public class CommonUtilTest {

    public void test() {
//        Constantpo
        ConstPool constPool = null;
    }

    @Test
    public void typeConversionTest() {
        int i = 123456;
        System.out.println("整型：" + i);
        byte[] bytes = CommonUtil.intToByteArray(i);
        System.out.println("字节数组：" + Arrays.toString(bytes));
        int ii = CommonUtil.byteArrayToInt(bytes);
        System.out.println("整型：" + ii);
    }
}
