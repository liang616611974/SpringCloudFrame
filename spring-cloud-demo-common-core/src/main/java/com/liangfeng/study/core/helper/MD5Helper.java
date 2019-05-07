package com.liangfeng.study.core.helper;


import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: MD5Helper
 * @Description:
 * @date  2018/9/27 14:14
 */
public class MD5Helper {

    /**
     * MD5 加密
     *
     * @param text 明文
     * @param key  密钥
     * @return 密文
     */
    public static String md5(String text, String key) {
        //加密后的字符串
        String encodeStr = null;
        try {
            encodeStr = DigestUtils.md5Hex(text + key);
        } catch (Exception e) {
            throw new RuntimeException("MD5加密发生异常", e);
        }
        return encodeStr;
    }

    /**
     * MD5 验证
     *
     * @param text 明文
     * @param key  密钥
     * @param md5  密文
     * @return true/false
     */
    public static boolean verify(String text, String key, String md5) {
        //根据传入的密钥进行验证
        String md5Text = md5(text, key);
        if (md5Text.equalsIgnoreCase(md5)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String text = "test1234";
        String key = "abcd";
        String encodeStr = md5(text, key);
        System.out.println("MD5加密后的字符串为:" + encodeStr);
        System.out.println("MD5验证是否成功：" + verify(text, key, encodeStr));
    }


}
