package com.liangfeng.study.core.helper;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Title: ClassHelper.java
 * @Description: 类帮助类，获取类的一些信息
 * @author Liangfeng
 * @date 2017/4/28 0028 上午 10:08
 * @version 1.0
 */
@SuppressWarnings("all")
public class ClassHelper {

	/**
	 * 构造私有化
	 */
	private ClassHelper(){}

	/**
	 * 获取类的方法集合
	 *
	 * @param clazz
	 * @param isContainParent
	 * @return
	 */
	private  Method[] getMethods(Class clazz, boolean isContainParent) {
		List<Method> methods = new ArrayList<>();
		Class tempClass = clazz;
		if (isContainParent) {
			while (tempClass != null) {
				methods.addAll(Arrays.asList(tempClass.getMethods()));
				tempClass = tempClass.getSuperclass();
			}
		} else {
			methods.addAll(Arrays.asList(tempClass.getMethods()));
		}
		return methods.toArray(new Method[]{});
	}
}
