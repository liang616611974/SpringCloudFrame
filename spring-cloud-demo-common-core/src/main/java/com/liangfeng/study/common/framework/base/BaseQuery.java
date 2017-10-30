package com.liangfeng.study.common.framework.base;

import com.liangfeng.study.common.framework.page.PageRequest;


public class BaseQuery extends PageRequest {

	protected static final int DEFAULT_PAGE_SIZE = 10;
	protected static final int DEFAULT_PAGE_WIDTH = 10;
	protected static final String DATE_PATTERN = "yyyy-MM-dd";
	protected static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	protected static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss.S";
	
	static {
		System.out.println("BaseQuery.DEFAULT_PAGE_SIZE=" + DEFAULT_PAGE_SIZE);
		System.out.println("BaseQuery.DEFAULT_PAGE_WIDTH=" + DEFAULT_PAGE_WIDTH);
	}
    
	public BaseQuery() {
		setPageSize(DEFAULT_PAGE_SIZE);
		setPageWidth(DEFAULT_PAGE_WIDTH);
	}
	  
}
