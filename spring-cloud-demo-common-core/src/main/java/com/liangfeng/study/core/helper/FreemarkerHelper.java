package com.liangfeng.study.core.helper;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Map;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: FreemarkerHelper
 * @Description:
 * @date  2018/8/19 0019 下午 10:03
 */
@Slf4j
public class FreemarkerHelper {

    private static final String ENCODING_UTF = "UTF-8";

    private FreemarkerHelper() { }

    /**
     * 获取Freemarker文件渲染后的字符串内容
     *
     * @param templateDir  模板所在目录对象
     * @param templateName 模板名称
     * @param paramMap     参数集合
     */
    public static String load4String(String templateDir, String templateName, Map<String, Object> paramMap){
        StringWriter writer = new StringWriter();
        load(templateDir,templateName,paramMap,writer);
        return writer.toString();
    }

    /**
     * 渲染Freemarker文件
     * @param templateDir  模板所在目录对象
     * @param templateName 模板名称
     * @param paramMap     参数集合
     * @param os           输出流
     */
    public static void load(String templateDir, String templateName, Map<String, Object> paramMap, OutputStream os){
        try{
            load(templateDir,templateName,paramMap,new OutputStreamWriter(os, ENCODING_UTF));
        }catch (Exception e){
            log.error("渲染Freemarker模版发生异常", e);
            throw new RuntimeException("渲染Freemarker模版发生异常", e);
        }
    }

    /**
     * 渲染Freemarker文件
     *
     * @param templateDir  模板所在目录对象
     * @param templateName 模板名称
     * @param paramMap     参数集合
     * @param os           输出流
     */
    public static void load(String templateDir, String templateName, Map<String, Object> paramMap, Writer os){
        Configuration cfg = null;
        Writer writer = null;
        try {
            //1.创建一个合适的configuration对象
            cfg = new Configuration(Configuration.VERSION_2_3_23);
            //2.设置模板所在目录
            cfg.setDirectoryForTemplateLoading(new File(templateDir));
            cfg.setDefaultEncoding(ENCODING_UTF);
            //4.设置模板异常
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            //5.获取或创建一个模板
            Template template = cfg.getTemplate(templateName);
            //6.写到对应的输出流
            writer = new BufferedWriter(os);
            template.process(paramMap, writer);
        }catch (Exception e){
            log.error("渲染Freemarker模版发生异常", e);
            throw new RuntimeException("渲染Freemarker模版发生异常", e);
        }finally {
            try {
                writer.flush();
                writer.close();
            } catch (Exception e) {
                log.error("关闭Freemarker文件流发生异常", e);
            }
        }

    }

}
