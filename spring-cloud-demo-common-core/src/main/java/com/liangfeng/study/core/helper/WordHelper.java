
package com.liangfeng.study.core.helper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;
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

    private static final String ENCODING_UTF = "UTF-8";

    private static final String DOC_TYPE_WORD = "WordDocument";

    /**
     * 私有化构造函数
     */
    private WordHelper() {
    }


    /**
     * 通过html文件创建word文档
     * @param templateDir
     * @param templateName
     * @param paramMap
     * @param os
     */
    public static void createWordByHtml(String templateDir, String templateName, Map<String, Object> paramMap,OutputStream os){
        String content = FreemarkerHelper.load4String(templateDir, templateName, paramMap);
        ByteArrayInputStream bais = null;
        POIFSFileSystem poifs = null;
        DirectoryEntry directory = null;
        try{
            bais = new ByteArrayInputStream(content.getBytes(ENCODING_UTF));
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

}

