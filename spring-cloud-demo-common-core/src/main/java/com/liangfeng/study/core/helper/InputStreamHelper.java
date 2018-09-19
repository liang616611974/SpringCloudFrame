package com.liangfeng.study.core.helper;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @Title InputStreamHelper.java
 * @Description 
 * @version 1.0
 * @author Liangfeng
 * @date 2018/9/3 14:37
 */
public class InputStreamHelper {

    private static final String ENCODING = "ISO-8859-1";

    private final static int BUFFER_SIZE = 4096;

    /**
     * 将InputStream转换成String,默认编码ISO-8859-1
     * @param in  输入流
     * @return
     */
    public static String inputStreamToString(InputStream in){
        return inputStreamToString(in, ENCODING);
    }

    /**
     * 将InputStream转换成String
     * @param in  输入流
     * @param encoding 编码
     * @return
     */
    public static String inputStreamToString(InputStream in,String encoding){
        String result = "";
        try{
            result = new String(inputStreamToByte(in), encoding);
        }catch (Exception e) {
            //log.error("InputStream转换成String发生异常", e);
            throw new RuntimeException("InputStream转换成String发生异常",e);
        }
        return result;
    }


    /**
     * 将InputStream转换成byte数组
     * @param in InputStream
     * @return byte[]
     */
    public static byte[] inputStreamToByte(InputStream in){
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        try{
            while((count = in.read(data,0,BUFFER_SIZE)) != -1){
                outStream.write(data, 0, count);
            }
            data = null;
        }catch (Exception e) {
            //log.error("InputStream转换成byte数组发生异常", e);
            throw new RuntimeException("InputStream转换成byte数组发生异常",e);
        }
        return outStream.toByteArray();
    }

    /**
     * 将String转换成InputStream
     * @param in
     * @return
     */
    public static InputStream stringToInputStream(String in) {
        try {
            return new ByteArrayInputStream(in.getBytes(ENCODING));
        } catch (Exception e) {
            //log.error("将String转换成InputStream发生异常", e);
            throw new RuntimeException("将String转换成InputStream发生异常",e);
        }
    }

    /**
     * 将byte数组转换成InputStream
     * @param in
     * @return
     */
    public static InputStream byteToInputStream(byte[] in){
        return new ByteArrayInputStream(in);
    }

}