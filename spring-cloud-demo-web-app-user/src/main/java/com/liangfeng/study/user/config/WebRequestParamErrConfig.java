package com.liangfeng.study.user.config;

import com.sellercube.aucenter.base.result.Response;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @Title WebErrorConfig.java
 * @Description Web请求参数错误全局控制器
 * @version 1.0
 * @author Liangfeng
 * @date 2017/6/12 0012 上午 10:43
 */
@Configuration
@ControllerAdvice
public class WebRequestParamErrConfig extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuffer sb = new StringBuffer();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			sb.append(error.getField());
			sb.append(error.getDefaultMessage());
			sb.append(",");
		}
		return new ResponseEntity<Object>(Response.paramErr(sb.toString()), headers, HttpStatus.OK);
	}
}
