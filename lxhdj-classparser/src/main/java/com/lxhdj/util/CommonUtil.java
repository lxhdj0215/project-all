package com.lxhdj.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CommonUtil {

	public static void copy(InputStream input, OutputStream output) throws IOException {
		byte[] buf = new byte[4096];
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
		byte[] array = new byte[4];
		int len = 4;
		for (int j = 0; j < 4; j++) {
			array[len - i - 1] = (byte) ((i >> i * 8) & 0xFF);
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
			value |= ((int) (arr[index] & 0xff)) << (8 * i);
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
			value |= ((int) (arr[index] & 0xff)) << (8 * i);
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
		for (int i = 0; i < 8; i++) {
			int index = len - i - 1;
			value |= ((long) (arr[index] & 0xff)) << (8 * i);
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
		int len = 8;
		byte[] arr = new byte[len];
		for (int i = 0; i < len; i++) {
			int index = len - i - 1;
			arr[index] = (byte) ((value >> 8 * i) & 0xff);
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
			value |= ((long) (arr[index] & 0xff)) << (8 * i);
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
			value |= ((long) (arr[index] & 0xff)) << (8 * i);
		}
		return value;
	}

}
