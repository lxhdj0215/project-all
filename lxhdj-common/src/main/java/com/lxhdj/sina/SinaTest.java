package com.lxhdj.sina;

import org.apache.commons.codec.binary.Hex;

import java.math.BigInteger;

public class SinaTest {
	public static void main(String[] args) {
		// rsaTest();
		test();
	}

	public static void test() {
		// String _modulus =
		// "987d424664085591afcc3733c8b80c76408bdd67792af4542a1f2bbf4a3b15d6da3a6c9486375d413d7423b96e36d8f293ca12cbcfc3f5e7252547dc1e410bc6e96a2e51970e7f461f867ac154f66dc8243ee14ce3ddc3c12147f072daa030db5ae64821882a8b5efbca0a08f550fc69bd43a2cae6ef672e8fa3b3987093e031";
		// BigInteger bigInteger = new BigInteger(_modulus, 16);
		// BigInteger b = new BigInteger("1000000000000", 10);
		// BigInteger b_hour = new BigInteger("3600", 10);
		// BigInteger b_day = new BigInteger("24", 10);
		// BigInteger b_year = new BigInteger("365000000000000", 10);
		// System.out.println(bigInteger.divide(b).divide(b_hour).divide(b_day).divide(b_year));
		long l1 = System.currentTimeMillis();
		String modulus = "10000000000";
		BigInteger b1 = new BigInteger(modulus, 10);
		BigInteger b2 = new BigInteger("0", 10);
		BigInteger b3 = new BigInteger("1", 10);
		while (b1.compareTo(b2) == 1) {
			// System.out.println(b2.toString(10));
			b2 = b2.add(b3);
		}
		long l2 = System.currentTimeMillis();
		System.out.println((l2 - l1) / 1000);
		System.out.println(1000000000 / ((l2 - l1) / 1000) * 10);
	}

	public static void rsaTest() {
		String modulus = "987d424664085591afcc3733c8b80c76408bdd67792af4542a1f2bbf4a3b15d6da3a6c9486375d413d7423b96e36d8f293ca12cbcfc3f5e7252547dc1e410bc6e96a2e51970e7f461f867ac154f66dc8243ee14ce3ddc3c12147f072daa030db5ae64821882a8b5efbca0a08f550fc69bd43a2cae6ef672e8fa3b3987093e031";
		BigInteger bigInteger = new BigInteger(modulus, 16);
		System.out.println(bigInteger.toString(2).getBytes().length);
		String publicExponent = "10001";
		String privateExponent = "15680015c19529edf9afc15013ad4292971d0b47ab4acc61c447fbec2e8f7cd15c71372ce26a833cd553a8702f78a39bea034400387a59dac8ec22da53a4edf2165212725644423545358f2c113acd8812d56d28fe917704d356c253d2c50b6383df2e715c7e6d4f7a3cbc3abecdc79814a9372d59042c2564d9ece2434f7901";
		String password = "meng850215";
		System.out.println("初始密码：" + password);
		try {
			String encryptPassword = SinaRsa.encryptByPublicKey(modulus, publicExponent, password);
			// System.out.println(encryptPassword.length());
			System.out.println("加密密码：" + encryptPassword);
			byte[] encryptContent = Hex.decodeHex(encryptPassword.toCharArray());
			String decryptPassword = SinaRsa.decryptByPrivateKey(modulus, privateExponent, encryptContent);
			System.out.println("解密密码：" + decryptPassword);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}