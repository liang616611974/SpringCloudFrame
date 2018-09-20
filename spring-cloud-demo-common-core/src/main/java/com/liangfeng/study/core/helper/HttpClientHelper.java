package com.liangfeng.study.core.helper;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: HttpClientHelper.java
 * @Description: HttpClient帮助类
 * @date 2017/4/28 0028 上午 10:09
 */
public class HttpClientHelper {

   /* private static final String CHARSET_UTF8 = "UTF-8";

    private static final String CONTENT_TYPE_UTF8 = "application/json";*/

    private static final String HTTP_SSL = "https";

    private static final int REQUEST_TIME_OUT = 30000;

    private HttpClientHelper() {
    }

    /**
     * 发送提交Json请求
     *
     * @param url     请求url，不能为null
     * @param headers 请求头，可以为null
     * @param param   请求参数，可以为null
     * @return
     */
    public static String postJson(String url, Map<String, String> headers, String param) {
        String result = null;
        try {
            //1.校验参数
            if (StringUtils.isBlank(url)) {
                throw new Exception("请求参数url不能为空!");
            }
            if (param == null) {
                param = "";
            }

            //2.创建请求参数实体
            StringEntity requestEntity = new StringEntity(param, ContentType.APPLICATION_JSON);
            /*requestEntity.setContentEncoding(CHARSET_UTF8);
            requestEntity.setContentType(CONTENT_TYPE_UTF8);*/

            //3.获取结果
            result = request(url, headers, requestEntity);
        } catch (Exception e) {
            throw new RuntimeException("httpclient发送postJson请求 异常", e);
        }
        return result;
    }

    /**
     * 发送提交表单请求
     *
     * @param url     请求url，不能为null
     * @param headers 请求头，可以为null
     * @param params  请求参数，可以为null
     * @return
     */
    public static String postForm(String url, Map<String, String> headers, Map<String, String> params) {
        String result = null;
        try {
            //1.校验参数
            if (StringUtils.isBlank(url)) {
                throw new Exception("请求参数url不能为空!");
            }
            if (params == null) {
                params = new HashMap<>();
            }
            //2.创建请求参数实体
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                nameValuePairs.add(new BasicNameValuePair(key, value));
            }
            HttpEntity requestEntity = new UrlEncodedFormEntity(nameValuePairs, Consts.UTF_8);
            //3.获取结果
            result = request(url, headers, requestEntity);
        } catch (Exception e) {
            throw new RuntimeException("httpclient发送postForm请求 异常", e);
        }
        return result;
    }

    /**
     * 发送上传文件请求
     *
     * @param url     请求URL
     * @param headers 请求头
     * @param params  请求参数
     * @param files   请求文件集合
     * @return
     */
    public static String upload(String url, Map<String, String> headers, Map<String, String> params, Map<String, File[]> files) {
        String result = null;
        try {
            //1.校验参数
            if (StringUtils.isBlank(url)) {
                throw new Exception("请求参数url不能为空!");
            }
            if (files == null) {
                throw new Exception("上传文件不能为空!");
            }
            // 2.创建请求参数实体
            HttpEntity requestEntity = null;
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();

            // 2.1 添加请求参数
            if (MapUtils.isNotEmpty(params)) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    entityBuilder.addTextBody(entry.getKey(), entry.getValue());
                }
            }

            // 2.2 添加请求文件
            if (MapUtils.isNotEmpty(files)) {
                for (Map.Entry<String, File[]> entry : files.entrySet()) {
                    File[] fileArr = entry.getValue();
                    if (ArrayUtils.isEmpty(fileArr)) {
                        continue;
                    }
                    for (File file : fileArr) {
                        entityBuilder.addPart(entry.getKey(), new FileBody(file));
                    }
                }
            }

            // 2.3 构建请求实体
            requestEntity = entityBuilder.build();

            // 3.获取结果
            result = request(url, headers, requestEntity);
        } catch (Exception e) {
            throw new RuntimeException("httpclient发送postFile请求 异常", e);
        }
        return result;
    }


    /**
     * 创建连接ssl httpclient
     *
     * @return
     */
    public static CloseableHttpClient createSSLClientDefault() {
        CloseableHttpClient client = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            client = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (Exception e) {
            client = HttpClients.createDefault();
        }
        return client;
    }

    /**
     * 发送post请求
     *
     * @param url
     * @param headers
     * @param requestEntity
     * @return
     */
    private static String request(String url, Map<String, String> headers, HttpEntity requestEntity) {
        //1.定义参数
        String body = ""; //响应体内容
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            // 2.创建httpClient实例
            if (url.startsWith(HTTP_SSL)) {
                httpClient = createSSLClientDefault();// 连接ssl实例
            } else {
                httpClient = HttpClients.createDefault();// 默认实例
            }
            // 3.创建httpPost实例
            HttpPost httpPost = new HttpPost(url);
            // 3.1 设置请求头信息
            if (MapUtils.isNotEmpty(headers)) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            // 4.设置表单实体
            httpPost.setEntity(requestEntity);
            // 5.设置请求配置
            RequestConfig defaultRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST)).setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
            RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig).setSocketTimeout(REQUEST_TIME_OUT).setConnectTimeout(REQUEST_TIME_OUT).setConnectionRequestTimeout(REQUEST_TIME_OUT).build();
            httpPost.setConfig(requestConfig);
            // 6.发送请求
            httpResponse = httpClient.execute(httpPost);
            // 7.处理返回结果
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            HttpEntity httpEntity = httpResponse.getEntity();
            if (null != httpEntity) {
                ContentType contentType = ContentType.getOrDefault(httpEntity);
                Charset charset = contentType.getCharset();
                body = EntityUtils.toString(httpEntity, charset);
                EntityUtils.consume(httpEntity);
            }
            if (statusCode < 200 || statusCode >= 300) {
                throw new Exception("处理失败status:" + statusCode + ",返回值:" + body);
            }
        } catch (Exception e) {
            throw new RuntimeException("httpclient发送post请求 异常", e);
        } finally {
            close(httpClient, httpResponse);
        }
        return body;
    }


    /**
     * 关闭httpclient资源
     *
     * @param httpClient
     * @param httpResponse
     */
    private static void close(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse) {
        if (null != httpResponse) {
            try {
                httpResponse.close();
            } catch (IOException e) {
            }
        }
        if (null != httpClient) {
            try {
                httpClient.close();
            } catch (IOException e) {
            }
        }
    }
}
