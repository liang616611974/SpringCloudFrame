package com.liangfeng.study.core.test;


import com.liangfeng.study.core.helper.WordHelper;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Test;

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

    @Test
    public void test() throws Exception{
        // 1.定义参数
        String templatePath = Class.class.getClass().getResource("/wordTemplate").getPath().replace("%20"," ");
        System.out.println(templatePath);
        File templateDir = new File(templatePath );// 模版所在的目录
        String templateName = "resume.doc";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", "梁峰");
        paramMap.put("position", "JAVA工程师");
        paramMap.put("sex", "男");
        paramMap.put("birthday", "1991年09月");
        OutputStream out = new FileOutputStream(new File("D:\\Development Files\\Document\\Word\\resume.doc"));
        WordHelper.createWordDoc(templateDir, templateName, paramMap, out);
    }

    @Test
    public void writeWordFile() throws Exception{
       // boolean w = false;
        String path = "D:\\Development Files\\Document\\Word\\";

        try {
            if (!"".equals(path)) {

                // 检查目录是否存在
                File fileDir = new File(path);
                if (fileDir.exists()) {

                    // 生成临时文件名称
                    String fileName = "a.doc";
                    String content = "<html>" +
                            "<head>你好</head>" +
                            "<body>" +
                            "<table>" +
                            "<tr>" +
                            "<td>信息1</td>" +
                            "<td>信息2</td>" +
                            "<td>t3</td>" +
                            "<tr>" +
                            "</table>" +
                            "</body>" +
                            "</html>";

                    byte b[] = content.getBytes("GBK");
                    ByteArrayInputStream bais = new ByteArrayInputStream(b);
                    POIFSFileSystem poifs = new POIFSFileSystem();
                    DirectoryEntry directory = poifs.getRoot();
                    DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
                    FileOutputStream ostream = new FileOutputStream(path+ fileName);
                    poifs.writeFilesystem(ostream);
                    bais.close();
                    ostream.close();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
       // return w;
    }
}
