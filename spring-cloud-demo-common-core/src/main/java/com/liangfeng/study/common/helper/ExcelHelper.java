package com.liangfeng.study.common.helper;

import com.liangfeng.study.common.constant.SystemConstant;
import com.liangfeng.study.common.constant.WebConstant;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title ExcelHelper
 * @Description Excel文件操作帮助类
 * @date 2017/7/13 0013 下午 8:26
 */
public class ExcelHelper {

    /**
     * 私有化
     */
    private ExcelHelper() {
    }

    /**
     * 导出文件到浏览器，供下载
     *
     * @param request      请求对象
     * @param response     响应对象
     * @param downloadName 下载文件名称
     * @param sheetName    表页名
     * @param heads        表头
     * @param data         表数据
     */
    public static void exportForDownload(HttpServletRequest request, HttpServletResponse response, String downloadName, String sheetName, List<ExcelHead> heads, List<Object> data) {
        try {

            // 1..判断浏览器
            String agent = request.getHeader(WebConstant.BROWSER_INFO_HEADER);
            /**
             * 设置不同浏览器中  下载文件的文件名编码
             * IE11浏览器的user-agent使用MSIE容易识别为firefox  导致出错
             */
            if (null != agent && -1 != agent.toLowerCase().indexOf("firefox")) {
                // firefox
                downloadName = new String(downloadName.getBytes(SystemConstant.SYS_ENCODING), "iso-8859-1");
            } else if (null != agent && -1 != agent.toUpperCase().indexOf("CHROME")) {
                //chrome
                downloadName = java.net.URLEncoder.encode(downloadName, SystemConstant.SYS_ENCODING);
            } else {
                //IE
                downloadName = java.net.URLEncoder.encode(downloadName, SystemConstant.SYS_ENCODING);
            }

            // 2.设置response
            response.reset();
            request.setCharacterEncoding(SystemConstant.SYS_ENCODING);
            response.setHeader(WebConstant.FILE_DOWNLOAD_HEADER, "attachment;filename=" + downloadName + ".xls");// 表示以附件形式可下载
            response.setContentType(WebConstant.EXCEL_CONTENT_TYPE + "; charset=" + SystemConstant.SYS_ENCODING);// 设置下载格式为EXCEL

            // 3.导出
            export(response.getOutputStream(), sheetName, heads, data);
        } catch (Exception e) {
            throw new RuntimeException("Excle文件下载发生异常");
        }

    }

    /**
     * 导出Excel表格文件
     *
     * @param os        文件输出流
     * @param sheetName 表页名
     * @param heads     表头
     * @param data      表数据
     */
    public static void export(OutputStream os, String sheetName, List<ExcelHead> heads, List<Object> data) {

        // 如果表头数据为空，则不执行
        if (CollectionUtils.isEmpty(heads)) {
            throw new RuntimeException("Excel表头数据为空");
        }

        // 1.定义变量
        WritableWorkbook book = null;

        try {

            // 2.创建Excel文件
            // 2.1 打开Excel工作表
            book = Workbook.createWorkbook(os);

            // 2.2 生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet(sheetName, 0);

            // 2.3 设置单元格字体
            // 2.3.1 表头字体格式
            WritableFont headFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
            // 2.3.2 内容字体格式
            WritableFont contentFont = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);

            // 2.4 设置单元格格式
            // 2.4.1 设置表头单元格样式
            WritableCellFormat headCellFormat = new WritableCellFormat(headFont);
            headCellFormat.setAlignment(Alignment.CENTRE);  //平行居中
            headCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);  //垂直居中
            headCellFormat.setWrap(true);
            // 2.4.2 设置内容单元格样式
            WritableCellFormat contentCellFormat = new WritableCellFormat(contentFont);
            contentCellFormat.setAlignment(Alignment.CENTRE);  //平行居中
            contentCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);  //垂直居中
            contentCellFormat.setWrap(true);

            // 2.5 设置表头
            for (int i = 0; i < heads.size(); i++) {
                // 2.5.1 设置表头的高度，选择最高的表头高度
                int height = 0;
                if (heads.get(i).getHeight() > height) {
                    height = heads.get(i).getHeight();
                    sheet.setRowView(0, height);
                }
                // 2.5.2 设置各列的宽度
                sheet.setColumnView(i, heads.get(i).getWidth());
                // 2.5.3 设置表头数据
                sheet.addCell(new Label(i, 0, heads.get(i).getName(), headCellFormat));
            }

            // 2.5 设置表格内容
            if (CollectionUtils.isNotEmpty(data)) {
                // 2.5.1 遍历数据集合
                for (int i = 0; i < data.size(); i++) {
                    // 转换数据
                    Map<String, Object> rowData = null; // 一行的数据
                    if (data.get(i) instanceof Map) {
                        rowData = (Map<String, Object>) data.get(i);
                    } else {
                        rowData = PropertyUtils.describe(data.get(i));
                    }
                    // 2.5.2 遍历表头集合
                    for (int j = 0; j < heads.size(); j++) {
                        String colKey = heads.get(j).getValue(); // 列key
                        Object colVal = rowData.get(colKey); // 列值
                        sheet.addCell(new Label(j, i + 1, String.valueOf(colVal), contentCellFormat));
                    }
                }
            }

            // 2.6 写入Excel工作表
            book.write();

            // 2.7 关闭Excel工作表
            book.close();

        } catch (Exception e) {
            throw new RuntimeException("导出Excel 发生异常", e);
        } finally {
            // 3.关闭输出流
            try {
                os.flush();
                os.close();
            } catch (Exception e) {
                throw new RuntimeException("关闭Excel文件输出流 发生异常", e);
            }
        }
    }

    public static class ExcelHead {
        /**
         * 表头名称
         */
        private String name;

        /**
         * 表头值键
         */
        private String value;

        /**
         * 表头高度
         */
        private int height;

        /**
         * 表头宽度
         */
        private int width;

        public ExcelHead() {
        }

        public ExcelHead(String name, String value, int height, int width) {
            this.name = name;
            this.value = value;
            this.height = height;
            this.width = width;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }

    public static void main(String[] args) throws Exception {
        FileOutputStream os = new FileOutputStream(new File("D:/Backup Files/测试/111.xls"));
        String sheetName = "第一页";
        List<ExcelHead> heads = new ArrayList<>();
        List<Object> data = new ArrayList<>();
        heads.add(new ExcelHead("名称", "name", 100, 30));
        heads.add(new ExcelHead("值键", "value", 200, 30));
        heads.add(new ExcelHead("高度", "height", 300, 50));
        heads.add(new ExcelHead("宽度", "width", 300, 50));
        data.add(new ExcelHead("名称1", "value1", 100, 30));
        data.add(new ExcelHead("名称2", "value2", 200, 30));
        data.add(new ExcelHead("名称3", "value3", 300, 50));
        /*
        heads.add(new ExcelHead("姓名","name",100,30));
        heads.add(new ExcelHead("年龄","age",200,30));
        heads.add(new ExcelHead("手机号码","mobile",300,50));

        Map<String, Object> row = new HashedMap();
        row.put("name", "张三");
        row.put("age",25);
        row.put("mobile", "1311806543");
        data.add(row);
        Map<String, Object> row2 = new HashedMap();
        row2.put("name", "李四");
        row2.put("age",23);
        row2.put("mobile", "1321806543");
        data.add(row2);
        */
        ExcelHelper.export(os, sheetName, heads, data);
    }


}
