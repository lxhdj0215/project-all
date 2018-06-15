package com.lxhdj.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CoderUtil {
    public static final String KEY_SHA = "SHA";

    public static final String KEY_MD5 = "MD5";

    public static byte[] decryptBASE64(String key) {
        byte[] bytes = Base64.decodeBase64(key);
        return bytes;
    }

    public static String encryptBASE64(byte[] bytes) {
        String encryptStr = Base64.encodeBase64String(bytes);
        return encryptStr;
    }

    public static String encryptBASE64(String str) {
        if (str == null) {
            throw new RuntimeException();
        }
        byte[] bytes = str.getBytes();
        String encryptStr = Base64.encodeBase64String(bytes);
        return encryptStr;
    }

    public static byte[] encryptMD5(byte[] data) {
        byte[] bytes = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
            md5.update(data);
            bytes = md5.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static String encryptMD5(String key) {
        byte[] data = key.getBytes();
        String encryptStr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
            md5.update(data);
            byte[] bytes = md5.digest();
            encryptStr = Hex.encodeHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptStr;
    }

    public static byte[] encryptSHA(byte[] data) {
        byte[] bytes = null;
        try {
            MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
            sha.update(data);
            bytes = sha.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static String encryptSHA(String key) {
        String encryptStr = null;
        try {
            byte[] data = key.getBytes();
            MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
            sha.update(data);
            byte[] bytes = sha.digest();
            encryptStr = Hex.encodeHexString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptStr;
    }
}
