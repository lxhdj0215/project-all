package com.lxhdj.util;

import javassist.bytecode.ConstPool;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by wangguijun1 on 2018/6/15.
 */
public class CommonUtilTest {

    private static final String DES = "DES";
    private static final String PADDING = "DES/ECB/PKCS5Padding";

    @Test
    public void readProperties() {
        File file = new File("D:\\test.properties");
        try {
            InputStream in = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(in);
            System.out.println(properties.get("serverside.recharge.privateKey"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file.exists());
    }

    @Test
    public void test() {
//        Constantpo
        ConstPool constPool = null;

        String FLOW_DES_KEY = "rsc8@#!P";
        String mobile = "15010847327";
        String encrypt = encrypt(mobile, FLOW_DES_KEY);
        System.out.println(encrypt);
        String decrypt = decrypt(encrypt, FLOW_DES_KEY);
        System.out.println(decrypt);
    }

    /**
     * 加密
     *
     * @param code
     * @param key
     * @return
     */
    public static String encrypt(String code, String key) {
        try {
            return new String(Base64.encodeBase64(encrypt(code.getBytes("utf-8"), key.getBytes("utf-8"))), "utf-8");
        } catch (Exception e) {
        }
        return null;
    }

    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

        // 一个SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(PADDING);

        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        // 正式执行加密操作
        return cipher.doFinal(src);
    }

    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        return cipher.doFinal(src);
    }

    /**
     * 解密
     *
     * @param data
     * @param key
     * @return
     */
    public static String decrypt(String data, String key) {
        try {
            return new String(decrypt(Base64.decodeBase64(data.getBytes("utf-8")), key.getBytes("utf-8")), "utf-8");
        } catch (Exception e) {
        }
        return null;
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
