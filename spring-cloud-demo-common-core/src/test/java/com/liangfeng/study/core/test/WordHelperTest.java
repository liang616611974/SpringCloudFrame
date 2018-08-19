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
import java.util.HashMap;
import java.util.Map;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: WordHelperTest
 * @Description:
 * @date  2018/8/12 0012 下午 10:03
 */
public class WordHelperTest {

    private String templatePath = Class.class.getClass().getResource("/wordTemplate").getPath().replace("%20", " ");// 模版所在的目录

    @Test
    public void textCreateWordByHtml() throws Exception {
        // 1.定义参数
        String templateName = "document.html"; // 模版文件
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
        paramMap.put("username", "梁峰（13418006543）");
        paramMap.put("position", "JAVA工程师");
        paramMap.put("sex", "男");
        paramMap.put("birthday", "1991年09月");
        // todo 补全完整的参数集合
        return paramMap;
    }
}
