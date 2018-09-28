package com.liangfeng.study.core.helper;

import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.tomcat.util.security.MD5Encoder;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @Title: Base64Helper.java
 * @Description: Base64加密解密帮助类
 * @author Liangfeng
 * @date 2017/4/28 0028 上午 10:07
 * @version 1.0
 */
public class Base64Helper {
	
	private Base64Helper(){}
	
	/**
	 * Base64加密成字符串
	 * @param b
	 * @return
	 */
	public static String encode(byte[] b) {
		String s = null;
		if (b != null) {
			s = new BASE64Encoder().encode(b);
		}
		return s;
	}

	/**
	 * Base64解密字符串
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static byte[] decode(String s) throws Exception {
		byte[] b = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			b = decoder.decodeBuffer(s);
		}
		return b;
	}

}
