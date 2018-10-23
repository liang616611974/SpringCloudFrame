package com.liangfeng.study.core.web.mvc.valid;

/**
* <p>Title: ValidatorType.java<／p>
* <p>Description: 验证器类型枚举<／p>
* @author Liangfeng
* @date 2017-1-15
* @version 1.0
 */
public enum CheckType{

	EMAIL(1),
	IDCARD(2),
	MOBILE(3),
	TELEPHONE(4),
	POST(5),
	INT(6),
	FLOAT(7),
	CHINESE(8),
	DATE(9),
	URL(10),
	IP(11);
	
	private final int value;
	private CheckType(int value) {
		this.value = value;
	}
	public int value() {
		return this.value;
	}
	
}