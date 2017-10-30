package com.liangfeng.study.common.component.id;

/**
 * @Title: IdGenerator.java
 * @Description: id生成器基类
 * @author Liangfeng
 * @date 2017/5/1 0001 8:32
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class IdGenerator {
	
	/**
	 * 生成主键id
	 * @return
	 */
	public <T> T generateId(){
		return (T)((Long)System.currentTimeMillis());
	}

}
