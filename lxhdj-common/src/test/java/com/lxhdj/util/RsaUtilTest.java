package com.lxhdj.util;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import java.security.Key;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

import static com.lxhdj.util.RsaUtil.getPrivateKey;
import static com.lxhdj.util.RsaUtil.getPublicKey;

/**
 * Created by wangguijun1 on 2018/6/14.
 */
public class RsaUtilTest {

    @Test
    public void signVerifyTest() {
        try {
            System.out.println("----------数字签名验证 开始----------");
            Map<String, Key> keyMap = RsaUtil.initKey();
            String publicKey = getPublicKey(keyMap);
            String privateKey = getPrivateKey(keyMap);
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyMap.get(RsaUtil.PUBLIC_KEY);
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyMap.get(RsaUtil.PRIVATE_KEY);
            System.out.println("模数：" + rsaPublicKey.getModulus().toString(16));
            System.out.println("公钥：" + rsaPublicKey.getPublicExponent().toString(16));
            System.out.println("私钥：" + rsaPrivateKey.getPrivateExponent().toString(16));
            String str = "weidailing";
            System.out.println("源字符串：" + str);
            String sign = RsaUtil.sign(str.getBytes(), privateKey);
            System.out.println("数字签名：" + sign);
            boolean bol = RsaUtil.verify(str.getBytes(), publicKey, sign);
            System.out.println("验证结果：" + bol);
            System.out.println("----------数字签名验证 结束----------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void encryptByPublicDecryptByPrivateTest_02() {
        try {
            System.out.println("----------公钥加密 私钥解密 开始----------");
            Map<String, Key> keyMap = RsaUtil.initKey();
            String publicKey = getPublicKey(keyMap);
            String privateKey = getPrivateKey(keyMap);
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyMap.get(RsaUtil.PUBLIC_KEY);
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyMap.get(RsaUtil.PRIVATE_KEY);
            String modulusHex = rsaPublicKey.getModulus().toString(16);
            String publicExponentHex = rsaPublicKey.getPublicExponent().toString(16);
            String privateExponentHex = rsaPrivateKey.getPrivateExponent().toString(16);
            System.out.println("模数：" + modulusHex);
            System.out.println("公钥：" + publicExponentHex);
            System.out.println("私钥：" + privateExponentHex);
            String str = "weidailing";
            System.out.println("源字符串：  " + str);
            String encryptStr = RsaUtil.encryptByPublicKey(modulusHex, publicExponentHex, str);
            System.out.println("加密字符串：" + encryptStr);
            char[] cs = encryptStr.toCharArray();
            byte[] bytes = Hex.decodeHex(cs);
            String decryptStr = RsaUtil.decryptByPrivateKey(modulusHex, privateExponentHex, bytes);
            System.out.println("解密字符串：" + decryptStr);
            System.out.println("----------公钥加密 私钥解密 结束----------");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void encryptByPublicDecryptByPrivateTest_01() {
        try {
            System.out.println("----------公钥加密 私钥解密 开始----------");
            Map<String, Key> keyMap = RsaUtil.initKey();
            String publicKey = getPublicKey(keyMap);
            String privateKey = getPrivateKey(keyMap);
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyMap.get(RsaUtil.PUBLIC_KEY);
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyMap.get(RsaUtil.PRIVATE_KEY);
            System.out.println("模数：" + rsaPublicKey.getModulus().toString(16));
            System.out.println("公钥：" + rsaPublicKey.getPublicExponent().toString(16));
            System.out.println("私钥：" + rsaPrivateKey.getPrivateExponent().toString(16));
            String str = "weidailing";
            System.out.println("源字符串：  " + str);
            byte[] encryptBytes = RsaUtil.encryptByPublicKey(str.getBytes(), publicKey);
            String encryptStr = RsaUtil.encryptBASE64(encryptBytes);
            System.out.println("加密字符串：" + encryptStr);
            encryptBytes = RsaUtil.decryptBASE64(encryptStr);
            byte[] decryptBytes = RsaUtil.decryptByPrivateKey(encryptBytes, privateKey);
            String decryptStr = new String(decryptBytes);
            System.out.println("解密字符串：" + decryptStr);
            System.out.println("----------公钥加密 私钥解密 结束----------");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void encryptByPrivateDecryptByPublicTest() {
        try {
            System.out.println("----------私钥加密 公钥解密 开始----------");
            Map<String, Key> keyMap = RsaUtil.initKey();
            String publicKey = getPublicKey(keyMap);
            String privateKey = getPrivateKey(keyMap);
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyMap.get(RsaUtil.PUBLIC_KEY);
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyMap.get(RsaUtil.PRIVATE_KEY);
            System.out.println("模数：" + rsaPublicKey.getModulus().toString(16));
            System.out.println("公钥：" + rsaPublicKey.getPublicExponent().toString(16));
            System.out.println("私钥：" + rsaPrivateKey.getPrivateExponent().toString(16));
            String str = "weidailing";
            System.out.println("源字符串：  " + str);
            byte[] encryptBytes = RsaUtil.encryptByPrivateKey(str.getBytes(), privateKey);
            String encryptStr = RsaUtil.encryptBASE64(encryptBytes);
            System.out.println("加密字符串：" + encryptStr);
            encryptBytes = RsaUtil.decryptBASE64(encryptStr);
            byte[] decryptBytes = RsaUtil.decryptByPublicKey(encryptBytes, publicKey);
            String decryptStr = new String(decryptBytes);
            System.out.println("解密字符串：" + decryptStr);
            System.out.println("----------私钥加密 公钥解密 结束----------");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
