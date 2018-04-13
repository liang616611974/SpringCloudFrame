package com.liangfeng.study.common.web.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: Response 系统响应对象
 * @Description:
 * @date 2017/5/9 0009 上午 11:00
 */
@Data
public class Response<T> {

    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码")
    private int code;

    /**
     * 返回消息
     */
    @ApiModelProperty(value = "消息内容")
    private String msg;

    /**
     * 是否成功
     */
    @ApiModelProperty(value = "操作是否成功")
    @JsonProperty("isSuccess")
    private boolean success;

    /**
     * 结果数据
     */
    @ApiModelProperty(value = "结果数据")
    @JsonProperty("data")
    private T responseBody;

    public Response() {
        super();
    }

    public Response(int code, String msg, boolean sucess, T responseBody) {
        this.code = code;
        this.msg = msg;
        this.success = sucess;
        this.responseBody = responseBody;
    }

    /**
     * 返回成功结果
     *
     * @return
     */
    public static Response success() {
        return new Response(Response.ResponseCode.OK.value(), "OK",true, null);
    }

    /**
     * 返回成功结果
     *
     * @param respBody
     * @param <T>
     * @return
     */
    public static <T> Response<T> success(T respBody) {
        return new Response<T>(Response.ResponseCode.OK.value(), "OK", true,respBody);
    }

    /**
     * 返回登录失效结果
     *
     * @return
     */
    public static Response noLogin() {
        return new Response(Response.ResponseCode.NO_LOGIN.value(), "登录失效", false, null);
    }

    /**
     * 返回不允许访问结果
     *
     * @return
     */
    public static Response notAllow() {
        return new Response(Response.ResponseCode.NOT_ALLOW.value(), "不允许访问", false,null);
    }

    /**
     * 返回不允许访问结果
     *
     * @param msg
     * @return
     */
    public static Response notAllow(String msg) {
        return new Response(Response.ResponseCode.NOT_ALLOW.value(), msg, false,null);
    }

    /**
     * 返回参数错误结果
     *
     * @return
     */
    public static Response paramErr() {
        return new Response(Response.ResponseCode.PARAM_ERR.value(), "参数错误", false,null);
    }

    /**
     * 返回参数错误结果
     *
     * @return
     */
    public static Response paramErr(String errMsg) {
        return new Response(Response.ResponseCode.PARAM_ERR.value(), errMsg,false, null);
    }

    /**
     * 返回服务器异常结果
     *
     * @return
     */
    public static Response serverErr() {
        return new Response(Response.ResponseCode.SERVER_ERR.value(), "服务器异常",false, null);
    }

    /**
     * 返回服务器异常结果
     *
     * @return
     */
    public static Response serverErr(String errMsg) {
        return new Response(Response.ResponseCode.SERVER_ERR.value(), errMsg,false, null);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    /**
     * 响应状态码 200-成功，300-没登陆或登录失效，403-不允许，340-参数错误，400-服务器异常
     */
    public enum ResponseCode {
        /**
         * 成功
         */
        OK(200),
        /**
         * 没登陆或登录失效
         */
        NO_LOGIN(300),
        /**
         * 不允许
         */
        NOT_ALLOW(403),
        /**
         * 参数错误
         */
        PARAM_ERR(340),
        /**
         * 服务器异常
         */
        SERVER_ERR(400);

        private final int value;

        private ResponseCode(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }

    }

}
