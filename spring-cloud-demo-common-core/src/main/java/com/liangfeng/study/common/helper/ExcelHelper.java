package com.liangfeng.study.common.helper;

import com.liangfeng.study.common.constant.AppConstant;
import com.liangfeng.study.common.constant.WebConstant;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title ExcelHelper.java
 * @Description Excel文件操作帮助类
 * @version 1.0
 * @author Liangfeng
 * @date 2018/2/3 0003 下午 8:22
 */
public class ExcelHelper {

    /**
     * 系统字符编码
     */
    private static final String APP_ENCODING = AppConstant.ENCODING; //"UTF-8"
    /**
     * 浏览器信息请求头
     */
    private static final String BROWSER_INFO_HEADER = WebConstant.RequestHeader.BROWSER_INFO;//"USER-AGENT";
    /**
     * 文件下载请求头
     */
    private static final String FILE_DOWNLOAD_HEADER = WebConstant.RequestHeader.FILE_DOWNLOAD;//"Content-Disposition";
    /**
     * EXCEL ContentType
     */
    private static final String EXCEL_CONTENT_TYPE = WebConstant.ResponseContentType.EXCEL; //"application/vnd.ms-excel";

    /**
     * 私有化
     */
    private ExcelHelper() {
    }

    /**
     * 导出文件到浏览器，供下载
     *
     * @param request          http请求对象
     * @param response         http响应对象
     * @param downloadName     下载名称
     * @param headerArrs       Excel表头集合，允许多行表头
     * @param data             表格数据
     */
    public static void exportForDownload(HttpServletRequest request, HttpServletResponse response, String downloadName, List<ExcelHeader[]> headerArrs, List<Object> data) {
        exportForDownload(request, response, downloadName, headerArrs, data, null);
    }

    /**
     * 导出文件到浏览器，供下载
     *
     * @param request          http请求对象
     * @param response         http响应对象
     * @param downloadName     下载名称
     * @param headerArrs       Excel表头集合，允许多行表头
     * @param data             表格数据
     * @param mergeColumnNames 需要合并行的列名集合
     */
    public static void exportForDownload(HttpServletRequest request, HttpServletResponse response, String downloadName, List<ExcelHeader[]> headerArrs, List<Object> data, String[] mergeColumnNames) {
        // 1.定义封装数据Map
        List<Map<String, Object>> maps = new ArrayList<>();
        try {
            for (Object obj : data) {
                maps.add(PropertyUtils.describe(obj));
            }
            exportForDownloadByMap(request, response, downloadName, headerArrs, maps, mergeColumnNames);
        } catch (Exception e) {
            throw new RuntimeException("Excle文件下载发生异常", e);
        }
    }

    /**
     * 导出文件到浏览器，供下载
     *
     * @param request      http请求对象
     * @param response     http响应对象
     * @param downloadName 下载名称
     * @param headerArrs   Excel表头集合，允许多行表头
     * @param data         表格数据
     */
    public static void exportForDownloadByMap(HttpServletRequest request, HttpServletResponse response, String downloadName, List<ExcelHeader[]> headerArrs, List<Map<String, Object>> data) {
        exportForDownloadByMap(request, response, downloadName, headerArrs, data, null);
    }

    /**
     * 导出文件到浏览器，供下载
     *
     * @param request          http请求对象
     * @param response         http响应对象
     * @param downloadName     下载名称
     * @param headerArrs       Excel表头集合，允许多行表头
     * @param data             表格数据
     * @param mergeColumnNames 需要合并行的列名集合
     */
    public static void exportForDownloadByMap(HttpServletRequest request, HttpServletResponse response, String downloadName, List<ExcelHeader[]> headerArrs, List<Map<String, Object>> data, String[] mergeColumnNames) {
        try {
            // 1.设置Excel工作表名称
            String sheetName = downloadName;
            // 2.设置下载Excel响应头
            setDownloadResponse(request, response, downloadName);
            // 3.导出
            exportByMap(response.getOutputStream(), sheetName, headerArrs, data, mergeColumnNames);
        } catch (Exception e) {
            throw new RuntimeException("Excle文件下载发生异常", e);
        }

    }

    /**
     * 导出Excel表格文件
     *
     * @param os               文件输出流
     * @param sheetName        Excel工作表名称
     * @param headerArrs       Excel表头集合，允许多行表头
     * @param data             Excel表格数据
     * @param mergeColumnNames 需要合并行的列名集合
     * @throws Exception
     */
    public static void export(OutputStream os, String sheetName, List<ExcelHeader[]> headerArrs, List<Object> data, String[] mergeColumnNames) throws Exception {
        // 1.定义封装数据Map
        List<Map<String, Object>> maps = new ArrayList<>();
        for (Object obj : data) {
            maps.add(PropertyUtils.describe(obj));
        }
        exportByMap(os, sheetName, headerArrs, maps, mergeColumnNames);
    }

    /**
     * 导出Excel表格文件
     *
     * @param os               文件输出流
     * @param sheetName        Excel工作表名称
     * @param headerArrs       Excel表头集合，允许多行表头
     * @param data             Excel表格数据
     * @param mergeColumnNames 需要合并行的列名集合
     */
    public static void exportByMap(OutputStream os, String sheetName, List<ExcelHeader[]> headerArrs, List<Map<String, Object>> data, String[] mergeColumnNames) {

        // 校验数据
        if (os == null || CollectionUtils.isEmpty(headerArrs)) {
            return;
        }

        // 1.定义变量
        WritableWorkbook book = null;
        WritableSheet sheet = null;
        try {

            // 2.创建Excel文件
            // 2.1 打开Excel工作表
            book = Workbook.createWorkbook(os);

            // 2.2 生成名为“第一页”的工作表，参数0表示这是第一页
            sheet = book.createSheet(sheetName, 0);

            // 3.创建表头
            createExcelHead(sheet, headerArrs);

            // 4.创建表体
            createExcelBody(sheet, headerArrs, data);

            // 5.合并某列单元格
            for (String mergeColumnName : mergeColumnNames) {
                createMergeColumnCells(sheet, headerArrs, data, mergeColumnName);
            }

            // 2.6 写入Excel工作表
            book.write();

            // 2.7 关闭Excel工作表
            book.close();
        } catch (Exception e) {
            throw new RuntimeException("导出Excel发生异常", e);
        } finally {
            closeOutputStream(os);
        }
    }

    /**
     * 创建Excle表头
     *
     * @param sheet    Excel工作表对象
     * @param headerArrs  Excel表头集合，允许多行表头
     * @throws Exception
     */
    private static void createExcelHead(WritableSheet sheet, List<ExcelHeader[]> headerArrs) throws Exception {
        // 校验数据
        if (sheet == null || CollectionUtils.isEmpty(headerArrs)) {
            return;
        }
        // 1.设置表头单元格样式
        // 1.1 字体样式
        WritableFont headFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
        // 1.2 表格内容样式
        WritableCellFormat headCellFormat = new WritableCellFormat(headFont);
        headCellFormat.setBackground(Colour.WHITE);
        headCellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
        headCellFormat.setAlignment(Alignment.CENTRE);  //平行居中
        headCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);  //垂直居中
        headCellFormat.setWrap(true);

        // 2.写入Excle表头
        for(int r=0; r<headerArrs.size(); r++){
            ExcelHeader[] headers = headerArrs.get(r);
            int rowIndex = r;
            int colIndex = 0;
            for (int c = 0; c< headers.length; c++) {
                // 2.1 设置表头的高度，选择最高的表头高度
                int height = 0;
                ExcelHeader header = headers[c];
                if (header.getHeight() > height) {
                    height = header.getHeight();
                    sheet.setRowView(rowIndex, height);
                }
                // 2.2 设置各列的宽度
                if(c>0){
                    colIndex = colIndex + headers[c-1].getColSpan();
                }
                sheet.setColumnView(colIndex, header.getWidth());
                // 2.3 设置表头数据
                sheet.addCell(new Label(colIndex, rowIndex, header.getName(), headCellFormat));
                // 合并列
                if(header.colSpan>1){
                    sheet.mergeCells(colIndex, rowIndex, colIndex + header.colSpan - 1, rowIndex);
                }
            }
        }

        // 3.合并表头行
        for(int r=0; r<headerArrs.size(); r++){
            ExcelHeader[] headers = headerArrs.get(r);
            int rowStartIndex = r;
            int rowEndIndex = 0;
            for (int c = 0; c< headers.length; c++) {
                ExcelHeader header = headers[c];
                int colIndex = c;
                if(header.rowSpan>1){
                    rowEndIndex = rowStartIndex + header.rowSpan -1;
                    sheet.mergeCells(colIndex, rowStartIndex,colIndex, rowEndIndex);
                }
            }
        }

    }

    /**
     * 创建Excle表体
     *
     * @param sheet      Excel工作表对象
     * @param headerArrs Excle表头集合
     * @param data       Excle表格数据
     */
    private static void createExcelBody(WritableSheet sheet, List<ExcelHeader[]> headerArrs, List<Map<String, Object>> data) throws Exception {
        // 校验数据
        if (sheet == null || CollectionUtils.isEmpty(headerArrs) || CollectionUtils.isEmpty(data)) {
            return;
        }

        // 1.设置一般的表体单元格样式
        // 1.1 设置字体样式
        WritableFont contentFont = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD);
        // 1.2 设置单元格样式
        WritableCellFormat contentCellFormat = new WritableCellFormat(contentFont);
        contentCellFormat.setBackground(Colour.WHITE);
        contentCellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
        contentCellFormat.setAlignment(Alignment.CENTRE);  //平行居中
        contentCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);  //垂直居中
        contentCellFormat.setWrap(true);
      /*  // 1.3 设置负数字体格式
        WritableFont minusContentFont = new WritableFont(WritableFont.ARIAL, 9, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
        WritableCellFormat minusContentCellFormat = new WritableCellFormat(minusContentFont);
        minusContentCellFormat.setBackground(Colour.WHITE);
        minusContentCellFormat.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
        minusContentCellFormat.setAlignment(Alignment.CENTRE);  //平行居中
        minusContentCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);  //垂直居中
        minusContentCellFormat.setWrap(true);*/

        // 2.写入表体内容
        ExcelHeader[] lastHeaders = headerArrs.get(headerArrs.size() - 1);
        int rowIndex = headerArrs.size(); // 第几行开始写入
        for (int i = 0; i < data.size(); i++) {
            // 转换数据
            Map<String, Object> rowData = data.get(i); // 一行的数据
            // 2.5.2 遍历最后一个表头集合
            for (int j = 0; j < lastHeaders.length; j++) {
                String colKey = lastHeaders[j].getValue(); // 列key
                String colVal = String.valueOf(rowData.get(colKey)); // 列值
                // 写入负数内容
               /* if (colVal.indexOf("-") == 0) {
                    colVal = "(" + colVal.substring(1, colVal.length()) + ")";
                    sheet.addCell(new Label(j, i + rowIndex, colVal, minusContentCellFormat));
                    continue;
                }*/
                // 写入一般内容
                sheet.addCell(new Label(j, i + rowIndex, colVal, contentCellFormat));
            }
        }

    }

    /**
     * 合并某列的行
     *
     * @param sheet      Excel工作表对象
     * @param headerArrs Excle表头集合
     * @param data       Excle表格数据
     * @param columnName 需要合并行的列名
     * @throws Exception
     */
    private static void createMergeColumnCells(WritableSheet sheet, List<ExcelHeader[]> headerArrs, List<Map<String, Object>> data, String columnName) throws Exception {
        if (sheet == null || CollectionUtils.isEmpty(headerArrs) || CollectionUtils.isEmpty(data) || StringUtils.isBlank(columnName)) {
            return;
        }
        // 组装合并对象集合
        int rowIndex = headerArrs.size();// 第几行开始合并
        List<Map<String, Integer>> merges = new ArrayList<>();
        Map<String, Integer> merge = new HashMap<>();
        merge.put("index", 0 + rowIndex);
        merge.put("rowspan", 1);
        // 2.5.1 遍历数据集合
        for (int i = 0; i < data.size(); i++) {
            // 转换数据
            Map<String, Object> rowData = data.get(i); // 一行的数据
            // 最后一个
            if (i == data.size() - 1) {
                if (data.get(i).get(columnName).equals(data.get(i - 1).get(columnName))) {
                    merges.add(merge);
                } else {
                    Map<String, Integer> map = new HashMap<>();
                    map.put("index", i + rowIndex);
                    map.put("rowspan", 1);
                    merges.add(map);
                }
                break;
            }
            if (data.get(i + 1).get(columnName).equals(data.get(i).get(columnName))) {
                merge.put("rowspan", merge.get("rowspan") + 1);
                continue;
            }
            merges.add(merge);
            merge = new HashMap<>();
            merge.put("index", i + rowIndex + 1);
            merge.put("rowspan", 1);
        }

        // 合并单元格
        ExcelHeader[] lastHeaders = headerArrs.get(headerArrs.size() - 1); // 最后一行表头
        int mergeColumnIndex = 0;
        int mergeRowStart = 0;
        int mergeRowEnd = 0;
        for (int i = 0; i < lastHeaders.length; i++) {
            if (lastHeaders[i].getValue().equals(columnName)) {
                mergeColumnIndex = i;
                break;
            }
        }
        for (Map<String, Integer> map : merges) {
            mergeRowStart = map.get("index");
            mergeRowEnd = map.get("index") + map.get("rowspan") - 1;
            sheet.mergeCells(mergeColumnIndex, mergeRowStart, mergeColumnIndex, mergeRowEnd);// 合并单元格
        }
    }

    /**
     * 关闭输出流
     *
     * @param os 文件输出流
     */
    private static void closeOutputStream(OutputStream os) {
        // 3.关闭输出流
        try {
            os.flush();
            os.close();
        } catch (Exception e) {
            throw new RuntimeException("关闭Excel文件输出流 发生异常", e);
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
      /*
        if (null != agent && -1 != agent.toLowerCase().indexOf("firefox")) {
            // firefox
            downloadName = new String(downloadName.getBytes(ENCODING), "iso-8859-1");
        } else if (null != agent && -1 != agent.toUpperCase().indexOf("CHROME")) {
            //chrome
            downloadName = java.net.URLEncoder.encode(downloadName, ENCODING);
        } else {
            //IE
            downloadName = java.net.URLEncoder.encode(downloadName, ENCODING);
        }*/

        if (!userAgent.contains("MSIE") && !userAgent.contains("Trident")) {
            downloadName = new String(downloadName.getBytes(APP_ENCODING), "iso-8859-1");
        } else {
            downloadName = URLEncoder.encode(downloadName, APP_ENCODING);
        }

        // 2.设置response
        response.reset();
        request.setCharacterEncoding(APP_ENCODING);
        response.setHeader(FILE_DOWNLOAD_HEADER, "attachment;filename=" + downloadName + ".xls");// 表示以附件形式可下载
        response.setContentType(EXCEL_CONTENT_TYPE + "; charset=" + APP_ENCODING);// 设置下载格式为EXCEL
    }

    @Data
    @AllArgsConstructor
    public static class ExcelHeader {
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
        /**
         * 合并列数
         */
        private int colSpan = 1;
        /**
         * 合并行数
         */
        private int rowSpan = 1;

        public ExcelHeader() {
        }

        public ExcelHeader(String name, String value, int height, int width) {
            this.name = name;
            this.value = value;
            this.height = height;
            this.width = width;
        }
    }

    public static void main(String[] args) throws Exception {
        // 1.设置文件输出流
        FileOutputStream os = new FileOutputStream(new File("F:/Backup Files/测试/111.xls"));
        // 2.设置Excel工作表名
        String sheetName = "第一页";
        // 3.设置Excel表头集合
        List<ExcelHeader[]> headerArrs = new ArrayList<>();
        List<ExcelHeader> heads1 = new ArrayList<>();
        heads1.add(new ExcelHeader("测试导出Excel表格","title",500,30,4,1));
        headerArrs.add(heads1.toArray(new ExcelHeader[heads1.size()]));
        List<ExcelHeader> heads2 = new ArrayList<>();
        heads2.add(new ExcelHeader("名称", "name", 100, 30));
        heads2.add(new ExcelHeader("值键", "value", 200, 30));
        heads2.add(new ExcelHeader("高度", "height", 300, 50,1,2));
        heads2.add(new ExcelHeader("宽度", "width", 300, 50,1,2));
        headerArrs.add(heads2.toArray(new ExcelHeader[heads2.size()]));
        List<ExcelHeader> heads3 = new ArrayList<>();
        heads3.add(new ExcelHeader("名称2", "name", 100, 30));
        heads3.add(new ExcelHeader("值键2", "value", 200, 30));
        heads3.add(new ExcelHeader("高度2", "height", 300, 50));
        heads3.add(new ExcelHeader("宽度2", "width", 300, 50));
        headerArrs.add(heads3.toArray(new ExcelHeader[heads3.size()]));
        // 3.设置Excel表体数据
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Map<String, Object> map = new HashMap<>();
            if (i > 0 && i <= 3) {
                map.put("name", "名称" + 1);
                map.put("value", "值" + 1);
            } else if (i > 3 && i <= 6) {
                map.put("name", "名称" + 2);
                map.put("value", "值" + 2);
            } else {
                map.put("name", "名称" + 3);
                map.put("value", "值" + 3);
            }
            map.put("height", "高度" + i * 100);
            map.put("width", "宽度" + i * 100);
            data.add(map);
        }
        // 4.设置和并列
        String[] mergeColumnNames = new String[]{"name", "value"};
        ExcelHelper.exportByMap(os, sheetName, headerArrs, data, mergeColumnNames);
    }


}
