package com.liangfeng.study.core.helper;

import com.liangfeng.study.core.constant.WebConstant;
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
import java.io.OutputStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

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
    private static final String APP_ENCODING = "UTF-8"; //"UTF-8"
    /**
     * 浏览器信息请求头
     */
    private static final String BROWSER_INFO_HEADER = "USER-AGENT";//"USER-AGENT";
    /**
     * 文件下载请求头
     */
    private static final String FILE_DOWNLOAD_HEADER = "Content-Disposition";//"Content-Disposition";
    /**
     * EXCEL ContentType
     */
    private static final String EXCEL_CONTENT_TYPE = "application/vnd.ms-excel"; //"application/vnd.ms-excel";

    /**
     * 私有化
     */
    private ExcelHelper() {
    }


    /**
     * 下载单页的Excel文件
     * @param request      http请求对象
     * @param response     http响应对象
     * @param downloadName 下载名称
     * @param datas 表格数据
     * @param clazz 数据的类型
     */
    public static void exportForDownloadByObj(HttpServletRequest request, HttpServletResponse response, String downloadName,List datas, Class clazz) {
        exportForDownloadByObj(request, response, downloadName, null, datas, clazz);
    }

    /**
     * 下载单页的Excel文件
     * @param request      http请求对象
     * @param response     http响应对象
     * @param downloadName 下载名称
     * @param extHeaderArrs 附加的表头集合
     * @param datas 表格数据
     * @param clazz 数据的类型
     */
    public static void exportForDownloadByObj(HttpServletRequest request, HttpServletResponse response, String downloadName,List<ExcelHeader[]> extHeaderArrs, List datas, Class clazz) {
        try {
            // 1.设置Excel工作表名称
            String sheetName = downloadName;
            // 2.设置下载Excel响应头
            setDownloadResponse(request, response, downloadName);
            // 3.导出
            exportByObj(response.getOutputStream(), sheetName, extHeaderArrs,datas, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Excle文件下载发生异常", e);
        }
    }


    /**
     * 下载多个sheet页的Excel文件
     * @param request      http请求对象
     * @param response     http响应对象
     * @param downloadName 下载名称
     * @param sheetNames 表页名集合
     * @param extHeaderArrsList 附加的表头集合
     * @param datas 多页数据
     * @param clazzs 数据的类型集合
     */
    public static void exportMultiForDownloadByObj(HttpServletRequest request, HttpServletResponse response, String downloadName,String[] sheetNames,List<List<ExcelHeader[]>> extHeaderArrsList, List<List> datas, Class[] clazzs) {
        try {
            // 2.设置下载Excel响应头
            setDownloadResponse(request, response, downloadName);
            // 3.导出
            exportMultiByObj(response.getOutputStream(), sheetNames,extHeaderArrsList,datas, clazzs);
        } catch (Exception e) {
            throw new RuntimeException("Excle文件下载发生异常", e);
        }
    }

    /**
     * 下载单页的Excel文件
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
     * 下载单页的Excel文件
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
     * 下载多个sheet页的Excel文件
     * @param request
     * @param response
     * @param downloadName
     * @param sheetNames
     * @param headerArrsList
     * @param datasList
     * @param mergeColumnNameArrs
     */
    public static void exportMultiForDownloadByMap(HttpServletRequest request, HttpServletResponse response, String downloadName, String[] sheetNames, List<List<ExcelHeader[]>> headerArrsList, List<List<Map<String, Object>>> datasList, List<String[]> mergeColumnNameArrs) {
        try {
            // 1.设置Excel工作表名称
            String sheetName = downloadName;
            // 2.设置下载Excel响应头
            setDownloadResponse(request, response, downloadName);
            // 3.导出
            exportMultiByMap(response.getOutputStream(), sheetNames, headerArrsList, datasList, mergeColumnNameArrs);
        } catch (Exception e) {
            throw new RuntimeException("Excle文件下载发生异常", e);
        }
    }



    /**
     * 导出单页Excel文件
     *
     * @param os        输出流
     * @param sheetName 表页名
     * @param extHeaderArrs 附加的表头集合
     * @param datas     数据
     * @param clazz     数据类型
     */
    public static void exportByObj(OutputStream os, String sheetName,List<ExcelHeader[]> extHeaderArrs, List datas, Class clazz) {
        //  1.第一个sheet页
        String[] sheetNames = new String[]{sheetName};
        // 2.第一页的附加表头
        List<List<ExcelHeader[]>> extHeaderArrsList = new ArrayList<>();
        extHeaderArrsList.add(extHeaderArrs);
        // 3.第一页的数据
        List<List> datasList = new ArrayList<>();
        datasList.add(datas);
        // 4.第一页的数据类型
        Class[] clazzs = new Class[]{clazz};
        // 5.导出
        exportMultiByObj(os,sheetNames,extHeaderArrsList,datasList,clazzs);
    }

    /**
     * 导出多个sheet页Excel表格文件
     * @param os 输出流
     * @param sheetNames 表页名集合
     * @param extHeaderArrsList 附加的表头集合
     * @param datas 多页数据
     * @param clazzs 数据的类型集合
     */
    public static void exportMultiByObj(OutputStream os, String[] sheetNames,List<List<ExcelHeader[]>> extHeaderArrsList, List<List> datas, Class[] clazzs) {
        List<List<ExcelHeader[]>> headerArrsList = new ArrayList<>();
        List<List<Map<String, Object>>> datasList = new ArrayList<>();
        //List<String[]> mergeColumnNameArrs = new ArrayList<>();
        // 组装表头，和表数据
        for (int i = 0; i < sheetNames.length; i++) {
            List<ExcelHeader[]> headerArrs = new ArrayList<>();
            List<ExcelHeader> heads = new ArrayList<>();
            // 1.添加附件表头
            if (CollectionUtils.isNotEmpty(extHeaderArrsList) && i<extHeaderArrsList.size() && CollectionUtils.isNotEmpty(extHeaderArrsList.get(i))) {
                headerArrs.addAll(extHeaderArrsList.get(i));
            }
            Field[] fields = getFields(clazzs[i], true);
            for (Field field : fields) {
                boolean isHasAnno = field.isAnnotationPresent(ExcelColumn.class);
                if (!isHasAnno) {
                    continue;
                }
                ExcelColumn property = field.getAnnotation(ExcelColumn.class);
                heads.add(new ExcelHeader(property.name(), StringUtils.isBlank(property.value()) ? field.getName() : property.value(),
                        property.order(), field.getType().getSimpleName(), property.pattern(), property.height(), property.width(),1,1));
            }
            headerArrs.add(heads.toArray(new ExcelHeader[]{}));
            List<Map<String, Object>> dataMaps = new ArrayList<>();
            for (Object data : datas.get(i)) {
                try {
                    dataMaps.add(PropertyUtils.describe(data));
                } catch (Exception e) {
                    throw new RuntimeException("对象转成Map发生异常", e);
                }
            }
            // 添加进集合
            headerArrsList.add(headerArrs);
            datasList.add(dataMaps);
        }
        exportMultiByMap(os, sheetNames, headerArrsList, datasList, null);
    }

    /**
     * 导出单页的Excel文件
     *
     * @param os               文件输出流
     * @param sheetName        Excel工作表名称
     * @param headerArrs       Excel表头集合，允许多行表头
     * @param datas             Excel表格数据
     * @param mergeColumnNames 需要合并行的列名集合
     */
    public static void exportByMap(OutputStream os, String sheetName, List<ExcelHeader[]> headerArrs, List<Map<String, Object>> datas, String[] mergeColumnNames) {
        // 1.第一个sheet页
        String[] sheetNames = new String[]{sheetName};
        // 2.第一页的表头
        List<List<ExcelHeader[]>> headerArrsList = new ArrayList<>();
        headerArrsList.add(headerArrs);
        // 3.第一页的数据
        List<List<Map<String, Object>>> datasList = new ArrayList<>();
        datasList.add(datas);
        // 4.第一页的合并列
        List<String[]> mergeColumnNameArrs = new ArrayList<>();
        mergeColumnNameArrs.add(mergeColumnNames==null?new String[]{}:mergeColumnNames);
        // 5.导出
        exportMultiByMap(os,sheetNames,headerArrsList,datasList,mergeColumnNameArrs);
    }


    /**
     * 导出多个sheet页的Excle文件
     * @param os
     * @param sheetNames
     * @param headerArrsList
     * @param datasList
     * @param mergeColumnNameArrs
     */
    public static void exportMultiByMap(OutputStream os, String[] sheetNames, List<List<ExcelHeader[]>> headerArrsList, List<List<Map<String, Object>>> datasList, List<String[]> mergeColumnNameArrs) {

        // 校验数据
        if (os == null || CollectionUtils.isEmpty(headerArrsList)) {
            return;
        }

        // 1.定义变量
        WritableWorkbook book = null;
        WritableSheet sheet = null;
        try {

            // 2.创建Excel文件
            // 2.1 打开Excel工作表
            book = Workbook.createWorkbook(os);

            // 3.遍历创建多个sheet页
            for (int i = 0; i < sheetNames.length; i++) {

                // 3.1 生成名为“第一页”的工作表，参数0表示这是第一页
                sheet = book.createSheet(sheetNames[i], i);

                // 3.2 创建表头
                createExcelHead(sheet, headerArrsList.get(i));

                // 3.3 创建表体
                createExcelBody(sheet, headerArrsList.get(i), datasList.get(i));

                // 3.4 合并某列单元格
                if(CollectionUtils.isNotEmpty(mergeColumnNameArrs)){
                    for (String mergeColumnName : mergeColumnNameArrs.get(i)) {
                        createMergeColumnCells(sheet, headerArrsList.get(i), datasList.get(i), mergeColumnName);
                    }
                }

            }

            // 4.写入Excel工作表
            book.write();

            // 5. 关闭Excel工作表
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
                String colVal = formatVal(lastHeaders[j],rowData.get(colKey)); // 列值
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

    /**
     * 格式化显示内容
     *
     * @param header
     * @param val
     * @return
     */
    private static String formatVal(ExcelHeader header, Object val) {
        String formatVal = "";
        if (val==null){
            return formatVal;
        }
        // 如果是日期
        if ("Date".equals(header.getDataType())) {
            if (StringUtils.isBlank(header.getPattern())) {
                formatVal = DateHelper.formatDate((Date) val);
            } else {
                formatVal = DateHelper.format((Date) val, header.getPattern());
            }
        } else if ("BigDecimal".equals(header.getDataType())) {
            // 如果是BigDecimal
            if(StringUtils.isBlank(header.getPattern())){
                formatVal =  NumberFormat.getCurrencyInstance().format(val).replace("￥","");
            }else if ("%".equals(header.getPattern())) {
                formatVal = ((BigDecimal) val).multiply(new BigDecimal(100)).toString() + "%";
            } else {
                DecimalFormat df = new DecimalFormat(header.getPattern());
                formatVal = df.format(val);
            }
        } else if (isNumeric(String.valueOf(val))) {
            // 如果是数字
            if(StringUtils.isBlank(header.getPattern())){
                formatVal = String.valueOf(val);
            }else if("CURRENTY".equals(header.getPattern())){
                formatVal =  NumberFormat.getCurrencyInstance().format(val).replace("￥","");
            } else {
                DecimalFormat df = new DecimalFormat(header.getPattern());
                formatVal = df.format(val);
            }
        } else {
            formatVal = String.valueOf(val);
        }
        return formatVal;
    }

    /**
     * 判断是否数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        try {
            new BigDecimal(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 获取类的字段
     * @param clazz
     * @param isContainParent
     * @return
     */
    public static Field[] getFields(Class clazz,boolean isContainParent){
        List<Field> fields = new ArrayList<>();
        Class tempClass = clazz;
        if(isContainParent){
            while (tempClass != null){
                fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
                tempClass = tempClass.getSuperclass();
            }
        }else {
            fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
        }
        return fields.toArray(new Field[]{});
    }

    @Data
    @AllArgsConstructor
    public static class ExcelHeader implements Comparable<ExcelHeader> {
        /**
         * 表头名称
         */
        private String name;

        /**
         * 表头值键
         */
        private String value;

        /**
         * 表头顺序
         */
        private int order;

        /**
         * 数据类型，比如：String：String、BigDecimal:BigDecimal 等
         */
        private String dataType;

        /**
         * 数据展现形式，比如：yyyy-MM-dd、% 等
         */
        private String pattern;

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

        public ExcelHeader(String name, String value, int height, int width,int colSpan,int rowSpan) {
            this.name = name;
            this.value = value;
            this.height = height;
            this.width = width;
            this.colSpan = colSpan;
            this.rowSpan = rowSpan;
        }

        @Override
        public int compareTo(ExcelHeader o) {
            return this.order - o.order;
        }
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ExcelColumn {
        /**
         * 显示名称
         *
         * @return
         */
        public String name() default "";

        /**
         * 数据字段名称
         *
         * @return
         */
        public String value() default "";

        /**
         * 顺序
         * @return
         */
        public int order() default 128;

        /**
         * 数据字段的展现形式
         *
         * @return
         */
        public String pattern() default "";

        /**
         * 表头高度
         */
        public int height() default 300;

        /**
         * 表头宽度
         */
        public int width() default 30;

    }

    public static void main(String[] args) throws Exception { }


}
