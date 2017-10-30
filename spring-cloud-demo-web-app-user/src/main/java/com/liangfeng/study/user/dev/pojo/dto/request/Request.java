package com.liangfeng.study.user.dev.pojo.dto.request;

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
    public interface Create { }

    // 验证修改组
    public interface Modify { }

    // 验证删除组
    public interface Remove { }

    // 验证获取组
    public interface Get { }

    // 验证查询组
    public interface Query { }

    // 按照顺序验证组
    @GroupSequence({Default.class, Remove.class, Get.class, Query.class, Modify.class, Create.class,})
    public interface All { }
}

