package com.liangfeng.study.zuul.web.provider;


import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: DictFallbackProvider
 * @Description:
 * @date  2018/9/6 9:51
 */
public class DictFallbackProvider implements ZuulFallbackProvider {

    private static final String ENCODING = "UTF-8";

    @Override
    public String getRoute() {
        // 注意: 这里是route的名称，不是服务的名称，
        // 如果这里写成大写SPRING-CLOUD-DICT将无法起到回退作用
        return "spring-cloud-dict";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value(); // 200
            }

            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();// "ok"
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("字典服务暂不可用，请稍后重试!".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }
        };
    }
}
