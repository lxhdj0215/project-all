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
     * Big-Endian
     *
     * @param i
     * @return
     */
    public static byte[] intToByteArray(int i) {
        int len = Constants.CONSTANT_4;
        byte[] array = new byte[len];
        for (int j = 0; j < len; j++) {
            array[len - i - 1] = (byte) ((i >> i * Constants.CONSTANT_8) & Constants.CONSTANT_FF);
        }
        return array;
    }

    /**
     * Big-Endian
     *
     * @param d
     * @return
     */
    public static byte[] doubleToByteArray(double d) {
        long value = Double.doubleToLongBits(d);
        byte[] arr = new byte[Constants.CONSTANT_8];
        for (int i = 0; i < Constants.CONSTANT_8; i++) {
            int index = Constants.CONSTANT_8 - i - 1;
            arr[index] = (byte) ((value >> Constants.CONSTANT_8 * i) & Constants.CONSTANT_FF);
        }
        return arr;
    }

    /**
     * Big-Endian
     *
     * @param arr
     * @return
     */
    public static short byteArrayToShort(byte[] arr) {
        short value = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int index = len - i - 1;
            value |= ((long) (arr[index] & Constants.CONSTANT_FF)) << (Constants.CONSTANT_8 * i);
        }
        return value;
    }

    /**
     * Big-Endian
     *
     * @param arr
     * @return
     */
    public static int byteArrayToInt(byte[] arr) {
        int value = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int index = len - 1 - i;
            value |= ((int) (arr[index] & Constants.CONSTANT_FF)) << (Constants.CONSTANT_8 * i);
        }
        return value;
    }

    /**
     * Big-Endian
     *
     * @param arr
     * @return
     */
    public static float byteArrayToFloat(byte[] arr) {
        int value = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int index = len - 1 - i;
            value |= ((int) (arr[index] & Constants.CONSTANT_FF)) << (Constants.CONSTANT_8 * i);
        }
        float f = Float.intBitsToFloat(value);
        return f;
    }

    /**
     * Big-Endian
     *
     * @param arr
     * @return
     */
    public static double byteArrayToDouble(byte[] arr) {
        long value = 0;
        int len = arr.length;
        for (int i = 0; i < Constants.CONSTANT_8; i++) {
            int index = len - i - 1;
            value |= ((long) (arr[index] & Constants.CONSTANT_FF)) << (Constants.CONSTANT_8 * i);
        }
        return Double.longBitsToDouble(value);
    }

    /**
     * Big-Endian
     *
     * @param arr
     * @return
     */
    public static long byteArrayToLong(byte[] arr) {
        long value = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int index = len - i - 1;
            value |= ((long) (arr[index] & Constants.CONSTANT_FF)) << (Constants.CONSTANT_8 * i);
        }
        return value;
    }

}
