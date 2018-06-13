package com.lxhdj.util.rsa;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class Rsa extends Coder {
	public static final String KEY_ALGORTHM = "RSA";//
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	public static final String PUBLIC_KEY = "RSAPublicKey";// 公钥
	public static final String PRIVATE_KEY = "RSAPrivateKey";// 私钥

	public static void main(String[] args) {
		encryptDecrypt();

	}

	public static void test() {
		try {
			Map<String, Object> keyMap = initKey();
			RSAPublicKey publicKey = (RSAPublicKey) keyMap.get(PUBLIC_KEY);
			String _publicKey = getPublicKey(keyMap);
			System.out.println(_publicKey);
			// 对公钥解密
			System.out.println(publicKey.getEncoded().length);
			System.out.println(publicKey.getPublicExponent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void signVerify() {
		try {
			Map<String, Object> keyMap = initKey();
			String publicKey = getPublicKey(keyMap);
			String privateKey = getPrivateKey(keyMap);
			String msg = "wang";
			String sign = sign(msg.getBytes(), privateKey);
			System.out.println(sign);
			boolean bol = verify(msg.getBytes(), publicKey, sign);
			System.out.println(bol);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void encryptDecrypt() {
		try {
			Map<String, Object> keyMap = initKey();
			String publicKey = getPublicKey(keyMap);
			String privateKey = getPrivateKey(keyMap);
			RSAPublicKey rsaPublicKey = (RSAPublicKey) keyMap.get(PUBLIC_KEY);
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyMap.get(PRIVATE_KEY);
			System.out.println(rsaPublicKey.getModulus().toString(16));
			System.out.println(rsaPublicKey.getPublicExponent().toString(16));
			System.out.println(rsaPrivateKey.getModulus().toString(16));
			System.out.println(rsaPrivateKey.getPrivateExponent().toString(16));
			String msg = "wang";
			System.out.println("原密码：" + msg);
			System.out.println("私钥加密");
			byte[] b_encryptByPrivate = encryptByPrivateKey(msg.getBytes(), privateKey);
			byte[] b_decryptByPublic = decryptByPublicKey(b_encryptByPrivate, publicKey);
			String decryptByPublic = new String(b_decryptByPublic);
			System.out.println("公钥解密：" + decryptByPublic);

			byte[] b_encryptByPublic = encryptByPublicKey(msg.getBytes(), publicKey);
			System.out.println(b_encryptByPublic.length);
			byte[] b_decryptByPrivate = decryptByPrivateKey(b_encryptByPublic, privateKey);
			String decryptByPrivate = new String(b_decryptByPrivate);
			System.out.println("私钥解密：" + decryptByPrivate);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 校验数字签名
	 * 
	 * @param data
	 *            加密数据
	 * @param publicKey
	 *            公钥
	 * @param sign
	 *            数字签名
	 * @return
	 * @throws Exception
	 */
	public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
		// 解密公钥
		byte[] keyBytes = decryptBASE64(publicKey);
		// 构造X509EncodedKeySpec对象
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
		// 指定加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
		// 取公钥匙对象
		PublicKey publicKey2 = keyFactory.generatePublic(x509EncodedKeySpec);

		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(publicKey2);
		signature.update(data);
		// 验证签名是否正常
		return signature.verify(decryptBASE64(sign));

	}

	/**
	 * 用私钥对信息生成数字签名
	 * 
	 * @param data
	 *            //加密数据
	 * @param privateKey
	 *            //私钥
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		// 解密私钥
		byte[] keyBytes = decryptBASE64(privateKey);
		// 构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
		// 指定加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
		// 取私钥匙对象
		PrivateKey privateKey2 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(privateKey2);
		signature.update(data);

		return encryptBASE64(signature.sign());
	}

	/**
	 * 用公钥加密
	 * 
	 * @param data
	 *            加密数据
	 * @param key
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
		// 对公钥解密
		byte[] keyBytes = decryptBASE64(key);
		// 取公钥
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
		Key publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		return cipher.doFinal(data);
	}

	/**
	 * 用公钥解密
	 * 
	 * @param data
	 *            加密数据
	 * @param key
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {
		// 对私钥解密
		byte[] keyBytes = decryptBASE64(key);
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
		Key publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);

		return cipher.doFinal(data);
	}

	/**
	 * 用私钥加密
	 * 
	 * @param data
	 *            加密数据
	 * @param key
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
		// 解密密钥
		byte[] keyBytes = decryptBASE64(key);
		// 取私钥
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);

		return cipher.doFinal(data);
	}

	/**
	 * 用私钥解密 * @param data 加密数据
	 * 
	 * @param key
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {
		// 对私钥解密
		byte[] keyBytes = decryptBASE64(key);

		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORTHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		return cipher.doFinal(data);
	}

	/**
	 * 初始化密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> initKey() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORTHM);
		keyPairGenerator.initialize(1024);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		// 公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		// 私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);

		return keyMap;
	}

	/**
	 * 取得公钥，并转化为String类型
	 * 
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return encryptBASE64(key.getEncoded());
	}

	/**
	 * 取得私钥，并转化为String类型
	 * 
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return encryptBASE64(key.getEncoded());
	}
}
