package com.liangfeng.study.core.framework.base;

import com.liangfeng.study.core.framework.page.PageRequest;


public class BaseQuery extends PageRequest {

	protected static final int DEFAULT_PAGE_SIZE = 10;
	protected static final int DEFAULT_PAGE_WIDTH = 10;
    
	public BaseQuery() {
		setPageSize(DEFAULT_PAGE_SIZE);
		setPageWidth(DEFAULT_PAGE_WIDTH);
	}
	  
}
