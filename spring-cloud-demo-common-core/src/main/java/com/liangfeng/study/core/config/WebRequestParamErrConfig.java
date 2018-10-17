package com.liangfeng.study.core.config;

import com.liangfeng.study.core.web.dto.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @Title WebErrorConfig.java
 * @Description Web请求参数错误全局控制器,可以通过重写不同处理参数异常的方法，处理各种参数问题，把结果返回前台
 * @version 1.0
 * @author Liangfeng
 * @date 2017/6/12 0012 上午 10:43
 */
@Configuration
@ControllerAdvice
public class WebRequestParamErrConfig extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(WebRequestParamErrConfig.class);

	@Autowired
	private AppCommonConfig.AppConfig appConfig;

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errMsg = getParamErrMsg(ex.getBindingResult());
		return new ResponseEntity<Object>(Response.paramErr(errMsg), headers, HttpStatus.OK);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		// 是否打印异常的堆栈信息
		boolean isPrintStackTrace = Boolean.valueOf(appConfig.getPrintExceptionStackTrace());
		logger.error("请求参数解析失败:{}",isPrintStackTrace + ex.getMessage());
		return new ResponseEntity<Object>(Response.paramErr(isPrintStackTrace?ex.getMessage():"请求参数解析失败"), headers, HttpStatus.OK);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errMsg = getParamErrMsg(ex.getBindingResult());
		return new ResponseEntity<Object>(Response.paramErr(errMsg), headers, HttpStatus.OK);
	}

	/**
     * 获取参数错误消息
	 * @param bindingResult
     * @return
     */
	private String getParamErrMsg(BindingResult bindingResult) {
		StringBuffer sb = new StringBuffer();
		for (FieldError error : bindingResult.getFieldErrors()) {
			sb.append(error.getField());
			sb.append(error.getDefaultMessage());
			sb.append(",");
		}
		logger.info("请求参数验证失败:{}", sb.toString());
		return sb.toString();
	}
}
