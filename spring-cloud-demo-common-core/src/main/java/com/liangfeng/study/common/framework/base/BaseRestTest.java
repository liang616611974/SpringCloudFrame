package com.liangfeng.study.common.framework.base;

import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;
/**
 * @author Liangfeng
 * @version 1.0
 * @Title: BaseRestTest rest测试基类
 * @Description:
 * @date 2017/5/11 0011 下午 2:21
 */
public class BaseRestTest extends BaseTest {

    @Autowired
    protected TestRestTemplate testRestTemplate;


    @PostConstruct
    public void init() {
        // 使用Apache HttpComponents，可以实现session通话
        CloseableHttpClient httpClient = HttpClientBuilder.create().addInterceptorFirst(new HttpRequestInterceptor() {

            @Override
            public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {

            }
        }).build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        this.testRestTemplate.getRestTemplate().setRequestFactory(factory);
    }

    /**
     * @param url
     * @param params
     * @param responseType
     * @param urlVariables
     * @param <T>
     * @return
     */
    public <T> T postForm(String url, Map<String, Object> params, ParameterizedTypeReference<T> responseType, Object... urlVariables) {
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            map.add(entry.getKey(), String.valueOf(entry.getValue()));
        }
        return post(url, null, MediaType.APPLICATION_FORM_URLENCODED, map, responseType, urlVariables);
    }

    /**
     * 提交postJson数据请求
     *
     * @param url          请求地址
     * @param request      请求对象
     * @param responseType 响应结果对象类型
     * @param urlVariables url参数集合
     * @param <T>          响应结果对象泛型
     * @return
     */
    public <T> T postJson(String url, String request, ParameterizedTypeReference<T> responseType, Object... urlVariables) {
        return post(url, null, MediaType.APPLICATION_JSON, request, responseType, urlVariables);
    }

    /**
     * 提交file数据请求
     *
     * @param url
     * @param params
     * @param responseType
     * @param urlVariables
     * @param <T>
     * @return
     */
    public <T> T upload(String url, MultiValueMap<String, Object> params, ParameterizedTypeReference<T> responseType, Object... urlVariables) {
        return post(url, null, MediaType.MULTIPART_FORM_DATA, params, responseType, urlVariables);
    }



    /**
     * 提交post请求
     *
     * @param url          请求地址
     * @param headers      请求头
     * @param request      请求对象
     * @param mediaType    请求数据类型
     * @param responseType 响应结果对象类型
     * @param urlVariables 请求地址参数集合
     * @param <T>          响应结果对象泛型
     * @return
     */
    public <T> T post(String url, Map<String, String> headers, MediaType mediaType, Object request, ParameterizedTypeReference<T> responseType, Object... urlVariables) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (MapUtils.isNotEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpHeaders.add(entry.getKey(), entry.getValue());
            }
        }
        httpHeaders.setContentType(mediaType);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(request, httpHeaders);
        ResponseEntity<T> result = this.testRestTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType, urlVariables);
        return result.getBody();
    }

}
