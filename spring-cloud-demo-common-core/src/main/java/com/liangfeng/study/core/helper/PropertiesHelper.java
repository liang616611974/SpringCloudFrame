package com.liangfeng.study.core.helper;

import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

/**
* @Title: PropertiesHelper.java
* @Description: properties文件操作帮助类
* @author Liangfeng
* @date 2016-10-13
* @version 1.0
 */
public class PropertiesHelper {
	
	//propertie对象
	private static Properties p = null;
	
	private PropertiesHelper(){};
	
	/**
	 * 初始化properties文件
	 * @param properties
	 */
	public static void init(Properties properties){
		p = properties;
	}
	
	/**
	 * 获取properties对象
	 * @return
	 */
	public static Properties getProperties() {
		return p;
	}

	/**
	 * 获取配置文件字符串值
	 * @param key 配置文件的键 默认值为""
	 * @return
	 */
	public static String getStr(String key) {
		return getStr(key, null);
	}

	/**
	 * 获取配置文件字符串值
	 * @param key 配置文件的键
	 * @param defaulVal 默认值
	 * @return
	 */
	public static String getStr(String key,String defaulVal){
		if(StringUtils.isBlank(key)){
			if(defaulVal!=null){
				return defaulVal;
			}
			return "";
		}
		if (p.getProperty(key) == null) {
			if(defaulVal!=null){
				return defaulVal;
			}
			return "";
		} 
		return p.getProperty(key);
	}

	/**
	 * 获取配置文件Int值
	 * @param key 配置文件的键 默认值为0
	 * @return
	 */
	public static int getInt(String key){
		return getInt(key,0);
	}

	/**
	 * 获取配置文件Int值
	 * @param key 配置文件的键
	 * @param defaultVal 默认值
	 * @return
	 */
	public static int getInt(String key,int defaultVal) {
		String val = getStr(key);
		if(StringUtils.isBlank(val)) {
			return defaultVal;
		}
		return Integer.parseInt(val);
	}

	/**
	 * 获取配置文件long值
	 * @param key 配置文件的键 默认值为0
	 * @return
	 */
	public static long getLong(String key){
		return getLong(key,0);
	}

	/**
	 * 获取配置文件Long值
	 * @param key
	 * @param defaultVal
	 * @return
	 */
	public static long getLong(String key,long defaultVal) {
		String val = getStr(key);
		if(StringUtils.isBlank(val)) {
			return defaultVal;
		}
		return Long.parseLong(val);
	}


	/**
	 * 获取配置文件boolean值
	 * @param key 配置文件的键 默认值为false
	 * @return
	 */
	public static boolean getBoolean(String key){
		return getBoolean(key,false);
	}

	/**
	 * 获取配置文件boolean值
	 * @param key 配置文件的键
	 * @param defaultVal 默认值
	 * @return
	 */
	public static boolean getBoolean(String key,boolean defaultVal){
		String val = getStr(key);
		if(StringUtils.isBlank(val)) {
			return defaultVal;
		}
		return Boolean.parseBoolean(val);
	}
	
	
	/**
	 * 从System.getProperty(key)及System.getenv(key)得到值 默认值""
	 * @param key
	 * @return
	 */
	public static String getSystemStr(String key) {
		String value = null;
		value = System.getProperty(key);
		if(StringUtils.isBlank(value)) {
			value = System.getenv(key);
		}
		if(StringUtils.isBlank(value)){
			value = "";
		}
		return value;
	}

	

}
