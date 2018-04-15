package com.liangfeng.study.core.web.dto.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

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

    // 验证新增组
    public interface Add { }

    // 验证修改组
    public interface Modify { }

    // 验证删除组
    public interface Remove { }

    // 验证获取组
    public interface Get { }

    // 验证查询组
    public interface Query { }

    // 按照顺序验证组
    @GroupSequence({Default.class, Remove.class, Get.class, Query.class, Modify.class, Add.class,})
    public interface All { }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}

