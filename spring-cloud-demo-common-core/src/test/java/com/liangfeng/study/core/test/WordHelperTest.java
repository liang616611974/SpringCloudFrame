package com.liangfeng.study.core.test;


import com.liangfeng.study.core.helper.WordHelper;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Test;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: WordHelperTest
 * @Description:
 * @date  2018/8/12 0012 下午 10:03
 */
public class WordHelperTest {

    private String templatePath = Class.class.getClass().getResource("/template").getPath().replace("%20", " ");// 模版所在的目录

    @Test
    public void textCreateWordByHtml() throws Exception {
        // 1.定义参数
        String templateName = "resume.html"; // 模版文件
        String outPath = "D:/Development Files/Document/Word"; // 输出文件目录
        OutputStream out = new FileOutputStream(new File(outPath + "/简历.doc"));
        WordHelper.createWordByHtml(templatePath, templateName, getParamMap(), out);
        //生成文件后，打开所在的文件夹
        Desktop.getDesktop().open(new File(outPath));
    }

    /**
     * 构造测试数据
     * @return
     */
    private Map<String, Object> getParamMap() {
        Map<String, Object> paramMap = new HashMap<>();
        // 添加基本信息
        Map<String, Object> baseInfo = new HashMap<>();
        baseInfo.put("username", "梁峰（13418006543）");
        baseInfo.put("post", "JAVA工程师");
        baseInfo.put("sex", "男");
        baseInfo.put("birthday", "1991年09月");
        baseInfo.put("workAge",4);
        baseInfo.put("education", "本科");
        baseInfo.put("email", "616611974@qq.com");
        baseInfo.put("address", "广州珠江新城豪宅");
        paramMap.put("i", baseInfo);
        // 添加技能特长
        List<String> skills = new ArrayList<>();
        skills.add("后台开发框架：熟悉SpringCloud、SpringBoot、SpringMVC 、SpringSecurity, Mybatis、 Freemarker等等");
        skills.add("数据库:Oracle、Mysql、Redis, 数据库设计工具 PowerDesigner。");
        skills.add("消息中间件：RabbitMq");
        skills.add("版本管理工具：Git,SVN. 开发工具：Idea，Eclipse");
        skills.add("前端：熟悉Jquery及Ajax的使用、EasyUI、Echart, 了解Agular js");
        skills.add("应用服务器：Tomcat 、Jboss、Nginx");
        skills.add("服务器系统：熟悉Linux CentOs");
        skills.add("熟悉Java基础，JVM原理，数据结构，多线程编程，数据库优化");
        paramMap.put("skills", skills);
        // 添加教育背景
        List<Map<String, Object>> educations = new ArrayList<>();
        Map<String, Object> educ = new HashMap<>();
        educ.put("period", "2010.09 - 2014.06");
        educ.put("school", "湛江师范学院");
        educ.put("major", "信息与计算科学");
        educ.put("education", "本科");
        educations.add(educ);
        paramMap.put("educations", educations);
        // 添加工作经历
        List<Map<String, Object>> works = new ArrayList<>();
        Map<String, Object> work = new HashMap<>();
        work.put("period", "2017.11 - 至今");
        work.put("company", "北京文思海辉金信软件有限公司");
        work.put("post", "高级工程师");
        works.add(work);
        Map<String, Object> work2 = new HashMap<>();
        work2.put("period", "2015.10 - 2017.10");
        work2.put("company", "广东网金控股股份有限公司");
        work2.put("post", "中级java工程师");
        works.add(work2);
        Map<String, Object> work3 = new HashMap<>();
        work3.put("period", "2014.04 - 2015.10");
        work3.put("company", "北京拓尔思信息技术股份有限公司广州分公司");
        work3.put("post", "java工程师");
        works.add(work3);
        paramMap.put("works", works);
        return paramMap;
    }
}
