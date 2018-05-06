package com.liangfeng.study.core.test;


import com.liangfeng.study.core.framework.base.BaseTest;
import com.liangfeng.study.core.helper.ExcelHelper;
import com.liangfeng.study.core.helper.ExcelHelper.*;
import com.liangfeng.study.core.helper.ObjectHelper;
import lombok.Data;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsServiceTest
 * @Description:
 * @date  2018/4/22 0022 上午 12:04
 */
/*@Transactional
@Rollback*/
/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class ExcelHelperTest extends BaseTest{


    @Test
    public void testExportByObj() throws Exception{
        FileOutputStream os = new FileOutputStream(new File("F:/Backup Files/测试/testExportByObj.xls"));
        String sheetName = "第一页";
        List<ExcelHelper.ExcelHeader[]> headerArrs = new ArrayList<>();
        List<ExcelHelper.ExcelHeader> heads1 = new ArrayList<>();
        heads1.add(new ExcelHelper.ExcelHeader("测试导出Excel表格","title",500,30,5,1));
        headerArrs.add(heads1.toArray(new ExcelHelper.ExcelHeader[heads1.size()]));
        List<ExcelObj> objs = new ArrayList<>();
        for (int i = 1; i <10; i++) {
            ExcelObj obj = new ExcelObj();
            ObjectHelper.initFieldsVal(obj,1);
            obj.setMoney(new BigDecimal(100000000));
            objs.add(obj);
        }
        ExcelHelper.exportByObj(os,sheetName,headerArrs,objs,ExcelObj.class);
    }

    @Test
    public void testExportMutiByObj() throws Exception{
        FileOutputStream os = new FileOutputStream(new File("F:/Backup Files/测试/testExportMutiByObj.xls"));
        List<String> sheetNames = new ArrayList<>();
        List<List<ExcelHeader[]>> headerArrsList = new ArrayList<>();
        List<ExcelHeader[]> headerArrs = new ArrayList<>();
        List<ExcelHeader> heads1 = new ArrayList<>();
        heads1.add(new ExcelHelper.ExcelHeader("测试导出Excel表格","title",500,30,5,1));
        headerArrs.add(heads1.toArray(new ExcelHelper.ExcelHeader[heads1.size()]));
        headerArrsList.add(headerArrs);
        List<List> objsList = new ArrayList<>();
        Class[] clazzs = new Class[]{ExcelObj.class,ExcelObj2.class};
        String sheetName1 = "第一页";
        List<Object> objs = new ArrayList<>();
        for (int i = 1; i <10; i++) {
            ExcelObj obj = new ExcelObj();
            ObjectHelper.initFieldsVal(obj,1);
            obj.setMoney(new BigDecimal(100000000));
            objs.add(obj);
        }
        String sheetName2 = "第二页";
        List<Object> obj2s = new ArrayList<>();
        for (int i = 1; i <10; i++) {
            ExcelObj2 obj2 = new ExcelObj2();
            ObjectHelper.initFieldsVal(obj2,2);
            obj2.setMoney(new BigDecimal(200000000));
            obj2s.add(obj2);
        }
        sheetNames.add(sheetName1);
        sheetNames.add(sheetName2);
        objsList.add(objs);
        objsList.add(obj2s);
        ExcelHelper.exportMultiByObj(os,sheetNames.toArray(new String[]{}),headerArrsList,objsList,clazzs);
    }


    @Test
    public void testExportByMap() throws Exception{
        FileOutputStream os = new FileOutputStream(new File("F:/Backup Files/测试/testExportByMap.xls"));
        List<String> sheetNames = new ArrayList<>();
        List<List<ExcelHelper.ExcelHeader[]>> headerArrsList = new ArrayList<>();
        List<List<Map<String, Object>>> datasList = new ArrayList<>();
        List<String[]> mergeColumnNameArrs = new ArrayList<>();
        // 1.设置文件输出流
        for (int i = 1; i <=1; i++) {
            addSheet(sheetNames, headerArrsList, datasList, mergeColumnNameArrs, i);
        }
        ExcelHelper.exportByMap(os, sheetNames.get(0), headerArrsList.get(0), datasList.get(0), mergeColumnNameArrs.get(0));
    }

    @Test
    public void testExportMutiByMap() throws Exception{
        FileOutputStream os = new FileOutputStream(new File("F:/Backup Files/测试/testExportMutiByMap.xls"));
        List<String> sheetNames = new ArrayList<>();
        List<List<ExcelHelper.ExcelHeader[]>> headerArrsList = new ArrayList<>();
        List<List<Map<String, Object>>> datasList = new ArrayList<>();
        List<String[]> mergeColumnNameArrs = new ArrayList<>();
        // 1.设置文件输出流
        for (int i = 1; i <=3; i++) {
            addSheet(sheetNames, headerArrsList, datasList, mergeColumnNameArrs, i);
        }

        ExcelHelper.exportMultiByMap(os, sheetNames.toArray(new String[]{}), headerArrsList, datasList, mergeColumnNameArrs);
    }

    private void addSheet(List<String> sheetNames, List<List<ExcelHelper.ExcelHeader[]>> headerArrsList, List<List<Map<String, Object>>> datasList, List<String[]> mergeColumnNameArrs, int i) {
        // 2.设置Excel工作表名
        String sheetName = "第" + i + "页";
        // 3.设置Excel表头集合
        List<ExcelHelper.ExcelHeader[]> headerArrs = new ArrayList<>();
        List<ExcelHelper.ExcelHeader> heads1 = new ArrayList<>();
        heads1.add(new ExcelHelper.ExcelHeader("测试导出Excel表格","title",500,30,4,1));
        headerArrs.add(heads1.toArray(new ExcelHelper.ExcelHeader[heads1.size()]));
        List<ExcelHelper.ExcelHeader> heads2 = new ArrayList<>();
        heads2.add(new ExcelHelper.ExcelHeader("名称", "name", 100, 30));
        heads2.add(new ExcelHelper.ExcelHeader("值键", "value", 200, 30));
        heads2.add(new ExcelHelper.ExcelHeader("高度", "height", 300, 50,1,2));
        heads2.add(new ExcelHelper.ExcelHeader("宽度", "width", 300, 50,1,2));
        headerArrs.add(heads2.toArray(new ExcelHelper.ExcelHeader[heads2.size()]));
        List<ExcelHelper.ExcelHeader> heads3 = new ArrayList<>();
        heads3.add(new ExcelHelper.ExcelHeader("名称2", "name", 100, 30));
        heads3.add(new ExcelHelper.ExcelHeader("值键2", "value", 200, 30));
        heads3.add(new ExcelHelper.ExcelHeader("高度2", "height", 300, 50));
        heads3.add(new ExcelHelper.ExcelHeader("宽度2", "width", 300, 50));
        headerArrs.add(heads3.toArray(new ExcelHelper.ExcelHeader[heads3.size()]));
        // 3.设置Excel表体数据
        List<Map<String, Object>> data = new ArrayList<>();
        for (int j = 1; j <= 10; j++) {
            Map<String, Object> map = new HashMap<>();
            if (j > 0 && j <= 3) {
                map.put("name", "名称" + 1);
                map.put("value", "值" + 1);
            } else if (j > 3 && j <= 6) {
                map.put("name", "名称" + 2);
                map.put("value", "值" + 2);
            } else {
                map.put("name", "名称" + 3);
                map.put("value", "值" + 3);
            }
            map.put("height", "高度" + j * 100);
            map.put("width", "宽度" + j * 100);
            data.add(map);
        }
        // 4.设置和并列
        String[] mergeColumnNames = new String[]{"name", "value"};

        // 5.添加到集合
        sheetNames.add(sheetName);
        headerArrsList.add(headerArrs);
        datasList.add(data);
        mergeColumnNameArrs.add(mergeColumnNames);
    }


    @Data
    public static class ExcelObj{
        @ExcelColumn(name="名称")
        private String name;
        @ExcelColumn(name="年龄")
        private int age;
        @ExcelColumn(name="性别")
        private String sex;
        @ExcelColumn(name="现金")
        private BigDecimal money;
        @ExcelColumn(name="出生日期")
        private Date birthday;
    }

    @Data
    public static class ExcelObj2{
        @ExcelColumn(name="名称")
        private String name;
        @ExcelColumn(name="年龄")
        private int age;
        //@ExcelColumn(name="性别")
        private String sex;
        @ExcelColumn(name="现金")
        private BigDecimal money;
        @ExcelColumn(name="出生日期")
        private Date birthday;
    }
}
