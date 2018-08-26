
package com.liangfeng.study.core.helper;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;


/**
 * @Title WordHelper.java
 * @Description word文档操作帮助类
 * @version 1.0
 * @author Liangfeng
 * @date 2018/8/19 0019 下午 9:43
 */
@Slf4j
public class WordHelper {

    /**
     * 系统字符编码
     */
    private static final String ENCODING = "UTF-8";
    private static final String ENCODING_ISO = "iso-8859-1";
    private static final String DOC_TYPE_WORD = "WordDocument";

    /**
     * 浏览器信息请求头
     */
    private static final String BROWSER_INFO_HEADER = "USER-AGENT";
    /**
     * 文件下载请求头
     */
    private static final String FILE_DOWNLOAD_HEADER = "Content-Disposition";
    /**
     * Word ContentType
     */
    private static final String CONTENT_TYPE_WORD = "application/msword";

    /**
     * 文件后缀
     */
    private static final String SUFFIX_WORD = ".doc";

    /**
     * 私有化构造函数
     */
    private WordHelper() {
    }

    /**
     * word 文件下载
     * @param request
     * @param response
     * @param downloadName
     * @param templateDir
     * @param templateName
     * @param paramMap
     */
    public static void exportForDownload(HttpServletRequest request, HttpServletResponse response, String downloadName,String templateDir, String templateName, Map<String, Object> paramMap) {
        try{
            // 1.设置下载Excel响应头
            setDownloadResponse(request, response, downloadName);
            // 2.导出
            createWordByHtml(templateDir,templateName,paramMap,response.getOutputStream());
        }catch (Exception e) {
            log.error("Word文件下载发生异常",e);
            throw new RuntimeException("Word文件下载发生异常", e);
        }

    }


    /**
     * 通过html文件创建word文档
     * @param templateDir
     * @param templateName
     * @param paramMap
     * @param os
     */
    public static void createWordByHtml(String templateDir, String templateName, Map<String, Object> paramMap,OutputStream os){
        // 1.获取html模版渲染后的内容
        String content = FreemarkerHelper.load4String(templateDir, templateName, paramMap);
        // 2.创建word文档
        ByteArrayInputStream bais = null;
        POIFSFileSystem poifs = null;
        DirectoryEntry directory = null;
        try{
            bais = new ByteArrayInputStream(content.getBytes(ENCODING));
            poifs = new POIFSFileSystem();
            directory = poifs.getRoot();
            DocumentEntry documentEntry = directory.createDocument(DOC_TYPE_WORD, bais);
            poifs.writeFilesystem(os);
        }catch (Exception e){
            log.error("创建word文档发生异常", e);
            throw new RuntimeException("创建word文档发生异常", e);
        }finally {
            try {
                bais.close();
                os.close();
            } catch (Exception e) {
                log.error("关闭word文档流发生异常", e);
            }
        }
    }

    /**
     * 设置下载响应头
     *
     * @param request      http请求对象
     * @param response     http响应对象
     * @param downloadName 下载名称
     * @throws Exception
     */
    private static void setDownloadResponse(HttpServletRequest request, HttpServletResponse response, String downloadName) throws Exception {
        // 1..判断浏览器
        String userAgent = request.getHeader(BROWSER_INFO_HEADER);
        /*
         * 设置不同浏览器中  下载文件的文件名编码
         * IE11浏览器的user-agent使用MSIE容易识别为firefox  导致出错
         */
        if (!userAgent.contains("MSIE") && !userAgent.contains("Trident")) {
            downloadName = new String(downloadName.getBytes(ENCODING), ENCODING_ISO);
        } else {
            downloadName = URLEncoder.encode(downloadName, ENCODING);
        }

        // 2.设置response
        response.reset();
        request.setCharacterEncoding(ENCODING);
        response.setHeader(FILE_DOWNLOAD_HEADER, "attachment;filename=" + downloadName + SUFFIX_WORD);// 表示以附件形式可下载
        response.setContentType(CONTENT_TYPE_WORD + "; charset=" + ENCODING);// 设置下载格式为EXCEL
    }


}

