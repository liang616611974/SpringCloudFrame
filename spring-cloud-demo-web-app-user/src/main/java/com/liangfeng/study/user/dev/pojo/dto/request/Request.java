package com.liangfeng.study.user.dev.pojo.dto.request;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: Request
 * @Description:
 * @date 2017/5/9 0009 上午 11:00
 */
public class Request<T> {

    private T requestBody;

    public T getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(T requestBody) {
        this.requestBody = requestBody;
    }
}
