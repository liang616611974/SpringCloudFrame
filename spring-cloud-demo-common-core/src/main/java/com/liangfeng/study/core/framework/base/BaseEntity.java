package com.liangfeng.study.core.framework.base;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
* @Title: BaseEntity.java
* @Description: 实体基类，包含一些共有属性
* @author Liangfeng
* @date 2016-10-13
* @version 1.0
 */
public class BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = -7200095849148417467L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


}
