package com.lxhdj.util;

import java.io.*;

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
	 * int到byte[]
	 * 
	 * @param i
	 * @return
	 */
	public static byte[] intToByteArray(int i) {
		byte[] result = new byte[4];
		// 由高位到低位
		result[0] = (byte) ((i >> 24) & 0xFF);
		result[1] = (byte) ((i >> 16) & 0xFF);
		result[2] = (byte) ((i >> 8) & 0xFF);
		result[3] = (byte) (i & 0xFF);
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
			int shift = (2 - 1 - i) * 8;
			value += (bytes[i] & 0x00FF) << shift;// 往高位游
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
			int shift = (len - 1 - i) * 8;
			value += (bytes[i] & 0x000000FF) << shift;// 往高位游
		}
		return value;
	}
}
