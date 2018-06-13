package com.lxhdj.util;

import com.lxhdj.common.Constants;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CommonUtil {
    private static final int FF = 0xFF;
    private static final int EIGHT = 8;

    public static void copy(InputStream input, OutputStream output) throws IOException {
        byte[] buf = new byte[Constants.BYTE_4K];
        int bytesRead = 0;
        while ((bytesRead = input.read(buf)) != -1) {
            output.write(buf, 0, bytesRead);
        }
    }

    public static byte[] readFileToByteArray(File file) throws IOException {
        InputStream input = new FileInputStream(file);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            copy(input, output);
            return output.toByteArray();
        } finally {
            input.close();
        }
    }

    /**
     * Big-Endian
     *
     * @param i
     * @return
     */
    public static byte[] intToByteArray(int i) {
        int len = Constants.FOUR;
        byte[] array = new byte[len];
        for (int j = 0; j < len; j++) {
            array[len - i - 1] = (byte) ((i >> i * EIGHT) & FF);
        }
        return array;
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
            value |= ((int) (arr[index] & FF)) << (EIGHT * i);
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
            value |= ((int) (arr[index] & FF)) << (EIGHT * i);
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
        for (int i = 0; i < EIGHT; i++) {
            int index = len - i - 1;
            value |= ((long) (arr[index] & FF)) << (EIGHT * i);
        }
        return Double.longBitsToDouble(value);
    }

    /**
     * Big-Endian
     *
     * @param d
     * @return
     */
    public static byte[] doubleToByteArray(double d) {
        long value = Double.doubleToLongBits(d);
        byte[] arr = new byte[EIGHT];
        for (int i = 0; i < EIGHT; i++) {
            int index = EIGHT - i - 1;
            arr[index] = (byte) ((value >> EIGHT * i) & FF);
        }
        return arr;
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
            value |= ((long) (arr[index] & FF)) << (EIGHT * i);
        }
        return value;
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
            value |= ((long) (arr[index] & FF)) << (EIGHT * i);
        }
        return value;
    }

}
