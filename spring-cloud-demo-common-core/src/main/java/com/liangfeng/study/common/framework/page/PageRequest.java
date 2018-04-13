package com.liangfeng.study.common.framework.page;

import lombok.Data;

import java.io.Serializable;

/**
* @Title: PageRequest.java
* @Description: 分页请求类
* @author Liangfeng
* @date 2016-11-10
* @version 1.0
 */
@Data
public class PageRequest implements Serializable {

	private static final long serialVersionUID = 1634988769228106224L;
	
	/**
	 * 页号码,页码从1开始
	 */
	private int pageNum;
	
	/**
	 * 分页大小
	 */
	private int pageSize;
	
	/**
	 * 页码范围大小
	 */
	private int pageWidth;
	
	/**
	 * 排序的多个列,如: username desc
	 */
	private String sortColumns;
	
}
