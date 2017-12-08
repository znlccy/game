package com.youda.encrypt;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author chencongye
 * @version 1.0.0
 * @date 2017-11-30
 * @introduce 创建SHA的加密方式
 */

@Component
public class SHAEncrpt {
	
	/**
	 * 声明SHA加密的秘钥
	 */
	public static final String KEY_SHA = "SHA";
	
	/*
	 * SHA加密
	 * @param data
	 * @return
	 * @throws Exception
	 */
	private static byte[] encryptSHA(byte[] data) throws Exception {
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);
		return sha.digest();
	}
	
	/**
	 * 实现返回加密之后的数据
	 * @param encrytData
	 * @return
	 */
	public static String SHAEncrption(String encrytData) {
		byte[] inputData = encrytData.getBytes();
		String encryptSecret = "";
		try {
			BigInteger sha = new BigInteger(new SHAEncrpt().encryptSHA(inputData));
			encryptSecret = sha.toString(32);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encryptSecret;
	}
	
	/**
	 * main函数验证SHA加密算法
	 * @param args
	 */
	/*public static void main(String[] args) {
		SHAEncrpt shaEncrpt = new SHAEncrpt();
		String encryptStr = "testkay";
		System.err.println("-obgvqnm3c2icfjt201gildjnmm2jhog");
		System.err.println("SHA:"+shaEncrpt.SHAEncrption(encryptStr));
	}*/
}
