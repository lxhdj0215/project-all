package com.lxhdj.util;

import org.junit.Test;

/**
 * Created by wangguijun1 on 2018/6/14.
 */
public class CoderUtilTest {

    @Test
    public void base64Test() {
        String str = "weidailing";
        System.out.println("----------base64 开始----------");
        System.out.println("源字符串   " + str);
        String encryptStr = CoderUtil.encryptBASE64(str);
        System.out.println("加密字符串 " + encryptStr);
        byte[] bytes = CoderUtil.decryptBASE64(encryptStr);
        String decryptStr = new String(bytes);
        System.out.println("解密字符串 " + decryptStr);
        System.out.println("----------base64 结束----------");
    }

    @Test
    public void md5Test() {
        String str = "weidailing";
        System.out.println("----------md5 开始----------");
        System.out.println("源字符串：" + str);
        String encryptStr = CoderUtil.encryptMD5(str);
        System.out.println("加密字符串：" + encryptStr.toUpperCase());
        System.out.println("----------md5 结束----------");
    }

    @Test
    public void shaTest() {
        String str = "weidailing";
        System.out.println("----------sha 开始----------");
        System.out.println("源字符串：" + str);
        String encryptStr = CoderUtil.encryptSHA(str);
        System.out.println("加密字符串：" + encryptStr.toUpperCase());
        System.out.println("----------sha 结束----------");
    }
}
