package com.liangfeng.study.core.helper;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: FileHelper
 * @Description: 文件操作工具类
 * @date  2018/9/11 16:43
 */
public class FileHelper {

    /**
     * 获取文件类型
     * @param filePath
     * @return
     */
    public static String getFileType(String filePath) {
        String fileType = "";
        try {
            fileType = checkType(inputStreamToHexStr(new FileInputStream(new File(filePath))));
        } catch (Exception e) {
            throw new RuntimeException("获取文件类型发生异常", e);
        }
        return fileType;
    }

    /**
     * 获取文件类型
     * @param inputStream
     * @return
     */
    public static String getFileType(InputStream inputStream) {
        return checkType(inputStreamToHexStr(inputStream));
    }

    /**
     * 根据文件流获取文件头十六进制字符串
     *
     * @param inputStream
     * @return
     */
    public static String inputStreamToHexStr(InputStream inputStream) {
        byte[] bytes = new byte[4];
        try {
            inputStream.read(bytes, 0, bytes.length);
        } catch (Exception e) {
            throw new RuntimeException("inputStreamToHexStr发生异常",e);
        }
        return bytesToHexStr(bytes);
    }

    /**
     * 根据文件字节数组获取文件头十六进制字符串
     *
     * @param src
     * @return
     */
    public static String bytesToHexStr(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toUpperCase();
    }


    /**
     * 常用文件的文件头如下：(以前六位为准)
     * JPEG (jpg)，文件头：FFD8FF
     * PNG (png)，文件头：89504E47
     * GIF (gif)，文件头：47494638
     * TIFF (tif)，文件头：49492A00
     * Windows Bitmap (bmp)，文件头：424D
     * CAD (dwg)，文件头：41433130
     * Adobe Photoshop (psd)，文件头：38425053
     * Rich Text Format (rtf)，文件头：7B5C727466
     * XML (xml)，文件头：3C3F786D6C
     * HTML (html)，文件头：68746D6C3E
     * Email [thorough only] (eml)，文件头：44656C69766572792D646174653A
     * Outlook Express (dbx)，文件头：CFAD12FEC5FD746F
     * Outlook (pst)，文件头：2142444E
     * MS Word/Excel (xls.or.doc)，文件头：D0CF11E0
     * MS Access (mdb)，文件头：5374616E64617264204A
     * WordPerfect (wpd)，文件头：FF575043
     * Postscript (eps.or.ps)，文件头：252150532D41646F6265
     * Adobe Acrobat (pdf)，文件头：255044462D312E
     * Quicken (qdf)，文件头：AC9EBD8F
     * Windows Password (pwl)，文件头：E3828596
     * ZIP Archive (zip)，文件头：504B0304
     * RAR Archive (rar)，文件头：52617221
     * Wave (wav)，文件头：57415645
     * AVI (avi)，文件头：41564920
     * Real Audio (ram)，文件头：2E7261FD
     * Real Media (rm)，文件头：2E524D46
     * MPEG (mpg)，文件头：000001BA
     * MPEG (mpg)，文件头：000001B3
     * Quicktime (mov)，文件头：6D6F6F76
     * Windows Media (asf)，文件头：3026B2758E66CF11
     * MIDI (mid)，文件头：4D546864
     * MP3 (mp3)，文件头：494433
     * M4A (m4a)，文件头：0000001
     * FLAC (flac)，文件头：664C61
     * WAV (wav)，文件头：524946
     */
    public static String checkType(String type) {
        if (type.contains("494433")) {
            return "mp3";
        }else if(type.contains("0000001")){
            return "m4a";
        }else if(type.contains("664C61")){
            return "flac";
        }else if(type.contains("524946")){
            return "wav";
        }else {
            return "";
        }
        /*if(type.contains("FFD8FF")){
            return "jpg";
        }else if(type.contains("89504E47")){
            return "png";
        }else if(type.contains("47494638")){
            return "gif";
        }else if(type.contains("49492A00")){
            return "tif";
        }else if(type.contains("424D")){
            return "bmp";
        }else{
            return "";
        }*/
    }

    public static void main(String[] args) throws Exception{
        File file =  new File("F:/Music/Voice/111.jpg");
         //File file =  new File("F:/Music/Voice/normal.mp3");// 49443303
       // File file =  new File("F:/Music/Voice/normal.m4a");// 0000001C
        //File file =  new File("F:/Music/Voice/normal.flac"); // 664C6143
        //File file =  new File("F:/Music/Voice/normal.wav"); // 52494646
//        File file =  new File("F:/Music/Voice/44.m4a"); // 00000018
        //File file =  new File("F:/Music/Voice/555.mp3"); // 49443303
      /*  InputStream inputStream = new FileInputStream(file);
        String hexStr = inputStreamToHexStr(inputStream);
        hexStr = hexStr.toUpperCase();
        System.out.println("HexStr===============" + hexStr);
        System.out.println("FileType===============" + checkType(hexStr));*/

       String fileType = getFileType("F:/Music/Voice/44.m4a");

        System.out.println("FileType===============" + fileType);

    }

}

