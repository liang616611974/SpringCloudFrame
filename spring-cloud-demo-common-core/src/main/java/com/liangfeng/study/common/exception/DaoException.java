package com.liangfeng.study.common.exception;

/**
 * @Title: DaoException.java
 * @Description: Dao层抛出的通用异常
 * @author Liangfeng
 * @date 2017/4/26 0026 21:38
 * @version 1.0
 */
public class DaoException extends RuntimeException{

	private static final long serialVersionUID = 1487159104443022977L;

	public DaoException(){
    	super();
    }
    public DaoException(String message){
    	super(message);
    }
    public DaoException(String message,Exception e){
    	super(message,e);
    }
}
