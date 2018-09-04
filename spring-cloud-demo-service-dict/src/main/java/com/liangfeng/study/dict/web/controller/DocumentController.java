package com.liangfeng.study.dict.web.controller;


import com.liangfeng.study.core.constant.AppConstant;
import com.liangfeng.study.core.helper.PdfHelper;
import com.liangfeng.study.core.helper.WordHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: DocumentController
 * @Description:
 * @date  2018/8/26 0026 下午 10:05
 */
@Api(description = "文档模块接口")
@RestController
public class DocumentController {

    /**
     * 模版所在路径
     */
    private static final String templatePath = Class.class.getClass().getResource(AppConstant.TEMPLATE_DIR).getPath().replace("%20", " ");// 模版所在的目录

    /**
     * 模版名称
     */
    private static final String templateName = "resume.html";

    @ApiOperation(value = "下载word文档")
    @RequestMapping(value = "/dict/doc/word",method = {RequestMethod.GET,RequestMethod.POST})
    public void exportWord(String downloadName, HttpServletRequest request, HttpServletResponse response){
        WordHelper.exportForDownload(request, response, downloadName, templatePath, templateName, getParamMap());
    }

    @ApiOperation(value = "下载word文档")
    @RequestMapping(value = "/dict/doc/pdf",method = {RequestMethod.GET,RequestMethod.POST})
    public void exportPDF(String downloadName, HttpServletRequest request, HttpServletResponse response){
        PdfHelper.exportForDownload(request, response, downloadName, templatePath, templateName, getParamMap());
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
        work2.put("post", "中级Java工程师");
        works.add(work2);
        Map<String, Object> work3 = new HashMap<>();
        work3.put("period", "2014.04 - 2015.10");
        work3.put("company", "北京拓尔思信息技术股份有限公司广州分公司");
        work3.put("post", "Java工程师");
        works.add(work3);
        paramMap.put("works", works);
        return paramMap;
    }


}
