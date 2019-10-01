package com.springaft.common.util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

/**
 * 名称：RSA非对称加密工具类<br>
 * 描述：用于RSA非堆成加密<br>
 *
 * @author JeffDu
 * @version 1.0
 * @since 1.0.0
 */
@Slf4j
public class RSAUtil {

	/**
	 * 公钥
	 */
	public static String publicKey; // 公钥
	/**
	 * 私钥
	 */
	public static String privateKey; // 私钥


	/**
	 * 生成公钥私钥对
	 */
	public static void generateKey() {
		// 1.初始化秘钥
		KeyPairGenerator keyPairGenerator;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			// 随机数生成器
			SecureRandom sr = new SecureRandom();
			// 设置512位长的秘钥
			keyPairGenerator.initialize(512, sr);
			// 开始公钥私钥
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
			// 进行转码
			publicKey = Base64.encodeBase64String(rsaPublicKey.getEncoded());
			// 进行转码
			privateKey = Base64.encodeBase64String(rsaPrivateKey.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			log.error("生成公钥私钥对失败", e);
		}
	}

	/**
	 * 私钥匙加密或解密
	 * 
	 * @param content
	 * @param privateKeyStr
	 * @return
	 */
	public static String encryptByprivateKey(String content, String privateKeyStr, int opmode) {
		// 私钥要用PKCS8进行处理
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyStr));
		KeyFactory keyFactory;
		PrivateKey privateKey;
		Cipher cipher;
		byte[] result;
		String text = null;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
			// 还原Key对象
			privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			cipher = Cipher.getInstance("RSA");
			cipher.init(opmode, privateKey);
			// 加密
			if (opmode == Cipher.ENCRYPT_MODE) {
				result = cipher.doFinal(content.getBytes());
				text = Base64.encodeBase64String(result);
			} else if (opmode == Cipher.DECRYPT_MODE) {
				// 解密
				result = cipher.doFinal(Base64.decodeBase64(content));
				text = new String(result, "UTF-8");
			}

		} catch (Exception e) {
			log.error("私钥加密失败", e);
		}
		return text;
	}

	/**
	 * 公钥匙加密或解密
	 * 
	 * @param content
	 * @param publicKeyStr
	 * @param opmode
	 * @return
	 */
	public static String encryptByPublicKey(String content, String publicKeyStr, int opmode) {
		// 公钥要用X509进行处理
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyStr));
		KeyFactory keyFactory;
		PublicKey publicKey;
		Cipher cipher;
		byte[] result;
		String text = null;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
			// 还原Key对象
			publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
			cipher = Cipher.getInstance("RSA");
			cipher.init(opmode, publicKey);
			if (opmode == Cipher.ENCRYPT_MODE) {
				// 加密
				result = cipher.doFinal(content.getBytes());
				text = Base64.encodeBase64String(result);
			} else if (opmode == Cipher.DECRYPT_MODE) {
				// 解密
				result = cipher.doFinal(Base64.decodeBase64(content));
				text = new String(result, "UTF-8");
			}
		} catch (Exception e) {
			log.error("公钥解密失败", e);
		}
		return text;
	}
}