package com.liangfeng.study.core.helper;

import org.apache.commons.codec.binary.Base64;

import java.util.UUID;

/**
 * @Title: UUIDHelper.java
 * @Description:UUID帮助操作类
 * @author Liangfeng
 * @date 2016-10-12
 * @version 1.0
 */
public class UUIDHelper {

	/**私有化，更不能创建*/
	private UUIDHelper() {}

	/**
	 * 生成java自带的UUID，32位字符
	 * @return
	 */
	public static String generateUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-","");
	}

	/**
	 * 生成Base64格式的压缩UUID，22位字符
	 * @return
	 */
	public static String generateCompressedUUID() {
		UUID uuid = UUID.randomUUID();
		return compressedUUID(uuid);
	}

	/**
	 * 压缩UUID
	 * @param uuid
	 * @return
	 */
	protected static String compressedUUID(UUID uuid) {
		byte[] byUuid = new
		byte[16];
		long least = uuid.getLeastSignificantBits();
		long most = uuid.getMostSignificantBits();
		long2bytes(most, byUuid, 0);
		long2bytes(least, byUuid, 8);
		String compressUUID = Base64.encodeBase64URLSafeString(byUuid);
		return compressUUID;
	}

	/**
	 * 压缩UUID
	 * @param uuidStr
	 * @return
	 */
	public static String compress(String uuidStr) {
		UUID uuid = UUID.fromString(uuidStr);
		return compressedUUID(uuid);
	}

	/**
	 * 解压UUID
	 * @param compressedUuid
	 * @return
	 */
	public static String uncompress(String compressedUuid) {
		if(compressedUuid.length() != 22) {
			throw new IllegalArgumentException("Invaliduuid!");
		}
		byte[] byUuid = Base64.decodeBase64(compressedUuid + "==");
		long most = bytes2long(byUuid, 0);
		long least = bytes2long(byUuid, 8);
		UUID uuid = new
		UUID(most, least);
		return uuid.toString();
	}
	
	private static void long2bytes(long value, byte[] bytes, int offset) {
		for(int i = 7; i > -1; i--) {
			bytes[offset++] = (byte) ((value >> 8 * i) & 0xFF);
		}
	}

	private static long bytes2long(byte[] bytes, int offset) {
		long value = 0;
		for(int i = 7; i > -1; i--) {
			value |= (((long) bytes[offset++]) & 0xFF) << 8 * i;
		}
		return value;
	}

	public static void main(String[] args) {
		/*
		 * String s = UUID.randomUUID().toString(); String s1 =
		 * UUID.randomUUID().toString(); System.out.println(s); //去掉“-”符号 s =
		 * s.substring
		 * (0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23
		 * )+s.substring(24); System.out.println(s);
		 */
		String s = UUIDHelper.generateCompressedUUID();
		System.out.println(s);
	}

}
