package com.lxhdj.util;

import com.lxhdj.constant.Constants;

public class CommonUtil {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * int到byte[]
     *
     * @param i
     * @return
     */
    public static byte[] intToByteArray(int i) {
        byte[] result = new byte[Constants.CONSTANT_4];
        // 由高位到低位
        result[0] = (byte) ((i >> Constants.CONSTANT_24) & Constants.CONSTANT_FF);
        result[1] = (byte) ((i >> Constants.CONSTANT_16) & Constants.CONSTANT_FF);
        result[2] = (byte) ((i >> Constants.CONSTANT_8) & Constants.CONSTANT_FF);
        result[Constants.CONSTANT_3] = (byte) (i & Constants.CONSTANT_FF);
        return result;
    }

    /**
     * byte[]转short
     *
     * @param bytes
     * @return
     */
    public static short byteArrayToShort(byte[] bytes) {
        short value = 0;
        // 由高位到低位
        for (int i = 0; i < 2; i++) {
            int shift = (2 - 1 - i) * Constants.CONSTANT_8;
            // 往高位游
            value += (bytes[i] & Constants.CONSTANT_00FF) << shift;
        }
        return value;
    }

    /**
     * byte[]转int
     *
     * @param bytes
     * @return
     */
    public static int byteArrayToInt(byte[] bytes) {
        int value = 0;
        int len = bytes.length;
        // 由高位到低位
        for (int i = 0; i < len; i++) {
            int shift = (len - 1 - i) * Constants.CONSTANT_8;
            // 往高位游
            value += (bytes[i] & Constants.CONSTANT_0000FF) << shift;
        }
        return value;
    }
}
