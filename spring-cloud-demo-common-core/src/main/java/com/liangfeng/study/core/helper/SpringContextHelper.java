package com.liangfeng.study.core.helper;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Title: SpringContextHelper.java
 * @Description: spring上下文帮助类
 * @author Liangfeng
 * @date 2017/4/28 0028 上午 10:10
 * @version 1.0
 */
@SuppressWarnings("all")
@Component
public class SpringContextHelper implements ApplicationContextAware {

	
	private static ApplicationContext applicationContext;//当前上下文容器
	
	/**
     * 设置当前上下文环境，此方法由spring自动装配
     */
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
	}
	
	/**
	 * 返回上下文容器
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	/**
	 * 根据注册对象的名称，从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 * @param id
	 * @return
	 */
	public static <T> T getBean(String id) {
		return (T) applicationContext.getBean(id);
	}

	/**
	 * 根据注册对象的类型，从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 * @param clazz
	 * @return
	 */
    public static <T> T getBean(Class<T> clazz) {
        Map beanMaps = applicationContext.getBeansOfType(clazz);
        if (beanMaps != null && !beanMaps.isEmpty()) {
            return (T) beanMaps.values().iterator().next();
        } else {
            return null;
        }
    }
	
	/**
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
	 * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.isSingleton(name);
	}
	
	/**
	 * 根据注册对象的名称获取注册对象的类型
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static Class getType(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getType(name);
	}
	
	/**
	 * 如果给定的bean名字在bean定义中有别名，则返回这些别名   
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 */
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return applicationContext.getAliases(name);
	}
	
}
