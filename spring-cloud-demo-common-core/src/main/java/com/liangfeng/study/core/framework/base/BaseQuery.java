package com.liangfeng.study.core.framework.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class BaseQuery implements Serializable {

	private static final long serialVersionUID = 1634988769228106224L;

	/**
	 * 排序的多个列,如: create_time desc id desc
	 */
	private String sortColumns;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
