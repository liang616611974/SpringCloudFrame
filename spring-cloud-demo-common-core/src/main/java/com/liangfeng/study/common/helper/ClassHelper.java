package com.liangfeng.study.common.helper;


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
	 * 获取对象实例的类名称
	 * @param instance
	 * @return
	 */
	public static String getClassName(Object instance) {
        String ret = "";
        Class clazz = getClazz(instance);
        if (null != clazz) {
            ret = clazz.getName();
        }
        return ret;
    }
	
	private static Class<?> getClazz(Object instance) {
		Class clazz = null;
		if (null != instance) {
			clazz = instance.getClass();
			if ((clazz != null) && clazz.getName().contains("$$")) {
				Class<?> superClass = clazz.getSuperclass();
				if ((superClass != null) && !Object.class.equals(superClass)) {
					return superClass;
				}
			}
		}
		return clazz;
	}
}
