
package com.liangfeng.study.core.helper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.Map;


/**
 * word文档操作帮助类
 */

public class WordHelper {

    private static final String ENCODING = "GBK";

    /**
     * 私有化构造函数
     */

    private WordHelper() {
    }


    /**
     * 创建word文档
     *
     * @param templateDir  模板所在目录对象
     * @param templateName 模板名称
     * @param paramMap     参数集合
     * @param os           输出流
     * @throws Exception
     */

    public static void createWordDoc(File templateDir, String templateName, Map<String, Object> paramMap, OutputStream os){
        Configuration cfg = null;
        Writer writer = null;
        try {
           //1.创建一个合适的configuration对象
           cfg = new Configuration(Configuration.VERSION_2_3_23);
           //2.设置模板所在目录
           cfg.setDirectoryForTemplateLoading(templateDir);
           cfg.setDefaultEncoding(ENCODING);
           //4.设置模板异常
           cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
           //5.获取或创建一个模板
           Template template = cfg.getTemplate(templateName);
           //6.写到对应的输出流
           writer = new BufferedWriter(new OutputStreamWriter(os, ENCODING));
           template.process(paramMap, writer);
            writer.flush();
            writer.close();
       }catch (Exception e){
           throw new RuntimeException("创建word文档发生异常", e);
       }

    }


}

