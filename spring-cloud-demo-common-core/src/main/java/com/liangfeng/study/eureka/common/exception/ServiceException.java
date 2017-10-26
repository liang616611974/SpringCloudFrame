package com.liangfeng.study.eureka.common.exception;

/**
 * @Title: ServiceException.java
 * @Description: Service层抛出的通用异常
 * @author Liangfeng
 * @date 2017/4/26 0026 21:38
 * @version 1.0
 */
public class ServiceException extends RuntimeException{
  
	private static final long serialVersionUID = 2129888337773839216L;

	public ServiceException(){
    	super();
    }
    public ServiceException(String message){
    	super(message);
    }
    public ServiceException(String message,Exception e){
    	super(message,e);
    }
}
