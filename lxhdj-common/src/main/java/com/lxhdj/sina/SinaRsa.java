package com.lxhdj.sina;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class SinaRsa {
	public static String encryptByPublicKey(String modulusHex, String exponentHex, String message) throws Exception {
		BigInteger modulus = new BigInteger(modulusHex, 16);
		BigInteger exponent = new BigInteger(exponentHex, 16);
		RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, exponent);

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		Key publicKey = keyFactory.generatePublic(rsaPublicKeySpec);

		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] encryptContent = cipher.doFinal(message.getBytes());
		String encryptPassword = new String(Hex.encodeHex(encryptContent));
		return encryptPassword;
	}

	public static String decryptByPrivateKey(String modulusHex, String exponentHex, byte[] data) throws Exception {
		BigInteger modulus = new BigInteger(modulusHex, 16);
		BigInteger exponent = new BigInteger(exponentHex, 16);
		RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(modulus, exponent);

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		Key privateKey = keyFactory.generatePrivate(rsaPrivateKeySpec);

		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptContent = cipher.doFinal(data);
		String decryptPassword = new String(decryptContent);
		return decryptPassword;
	}
}
