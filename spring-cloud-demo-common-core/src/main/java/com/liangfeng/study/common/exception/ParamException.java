package com.liangfeng.study.common.exception;

/**
 * @Title  ParamException
 * @Description 通用的参数异常，使用场景：在方法里面判断参数是否正确，如果不正确可以抛出该异常让外层处理
 * @version 1.0
 * @author Liangfeng
 * @date 2017/7/23 0023 上午 10:30
 */
public class ParamException extends RuntimeException{

    private static final long serialVersionUID = 2129888337773839216L;

    public ParamException() {
        super();
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }
}
