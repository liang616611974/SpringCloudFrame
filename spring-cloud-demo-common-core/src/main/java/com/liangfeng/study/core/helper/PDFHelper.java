
package com.liangfeng.study.core.helper;

import com.itextpdf.awt.AsianFontMapper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;


/**
 *  * @Title WordHelper.java
 *  * @Description word文档操作帮助类
 *  * @version 1.0
 *  * @author Liangfeng
 *  * @date 2018/8/19 0019 下午 9:43
 *  
 */
@Slf4j
public class PDFHelper {

    /**
     * 系统字符编码
     */
    private static final String ENCODING = "UTF-8";

    private static final String ENCODING_ISO = "iso-8859-1";

    /**
     * 浏览器信息请求头
     */
    private static final String BROWSER_INFO_HEADER = "USER-AGENT";
    /**
     * 文件下载请求头
     */
    private static final String FILE_DOWNLOAD_HEADER = "Content-Disposition";
    /**
     * PDF ContentType
     */
    private static final String CONTENT_TYPE_PDF = "application/pdf";
    /**
     * 文件后缀
     */
    private static final String SUFFIX_PDF = ".pdf";

    /**
     * 私有化构造函数
     */
    private PDFHelper() {
    }

    /**
     * word 文件下载
     *
     * @param request
     * @param response
     * @param downloadName
     * @param templateDir
     * @param templateName
     * @param paramMap
     */
    public static void exportForDownload(HttpServletRequest request, HttpServletResponse response, String downloadName, String templateDir, String templateName, Map<String, Object> paramMap) {
        try {
            // 1.设置下载Excel响应头
            setDownloadResponse(request, response, downloadName);
            // 2.导出
            createPDFByHtml(templateDir, templateName, paramMap, response.getOutputStream());
        } catch (Exception e) {
            log.error("PDF文件下载发生异常", e);
            throw new RuntimeException("PDF文件下载发生异常", e);
        }

    }


    /**
     * 通过html文件创建word文档
     *
     * @param templateDir
     * @param templateName
     * @param paramMap
     * @param os
     */
    public static void createPDFByHtml(String templateDir, String templateName, Map<String, Object> paramMap, OutputStream os) {
        // 1.获取html模版渲染后的内容
        String content = FreemarkerHelper.load4String(templateDir, templateName, paramMap);
        // 2.创建PDF
        Document document = new Document(PageSize.A4, 50, 50, 60, 60);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, os);
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(content.getBytes()), Charset.forName(ENCODING), new AsianFontProvider());
        } catch (Exception e) {
            log.error("创建PDF文档发生异常", e);
            throw new RuntimeException("创建PDF文档发生异常", e);
        } finally {
            try {
                document.close();
                os.close();
            } catch (Exception e) {
                log.error("关闭PDF文档流发生异常", e);
            }
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
            downloadName = new String(downloadName.getBytes(ENCODING), ENCODING_ISO);
        } else {
            downloadName = URLEncoder.encode(downloadName, ENCODING);
        }

        // 2.设置response
        response.reset();
        request.setCharacterEncoding(ENCODING);
        response.setHeader(FILE_DOWNLOAD_HEADER, "attachment;filename=" + downloadName + SUFFIX_PDF);// 表示以附件形式可下载
        response.setContentType(CONTENT_TYPE_PDF + "; charset=" + ENCODING);// 设置下载格式为EXCEL
    }

    /**
     * 这个Font是为了解决中文显示问题
     */
    public static class AsianFontProvider extends XMLWorkerFontProvider {

        public Font getFont(final String fontname, final String encoding, final boolean embedded, final float size, final int style, final BaseColor color) {
            BaseFont bf = null;
            try {
                bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            } catch (Exception e) {
                log.error("创建中文字体提供器发生异常",e);
            }
            Font font = new Font(bf, size, style, color);
            font.setColor(color);
            return font;
        }
    }
}

