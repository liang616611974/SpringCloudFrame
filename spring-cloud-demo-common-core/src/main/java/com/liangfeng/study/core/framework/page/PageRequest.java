package com.liangfeng.study.core.framework.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
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
	 * 排序的多个列,如: create_time desc id desc
	 */
	private String sortColumns;


	/**
	 * 分页开始索引
	 */
	private int begin;

	/**
	 * 分页结束索引
	 */
	private int end;

	public int getBegin() {
		return ((this.pageNum - 1) * this.pageSize);
	}

	public int getEnd() {
		return this.pageNum * this.pageSize;
	}
}
