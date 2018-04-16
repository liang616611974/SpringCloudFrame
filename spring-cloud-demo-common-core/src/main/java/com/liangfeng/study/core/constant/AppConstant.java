package com.liangfeng.study.core.constant;

/**
 * @Title: AppConstant
 * @Description: 系统常量
 * @author Liangfeng
 * @date 2017/4/28 0028 上午 10:03
 * @version 1.0
 */
public class AppConstant {

	/** 应用基本包引用 */
	public static final String BASE_PACKAGE = "com.liangfeng.study";

	/** 应用字符编码 */
	public static final String ENCODING = "UTF-8";
	
	/** 应用行分隔符 */
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	/** 应用的地区 */
	public static final String LOCALE = "zh";

	/** 应用的时区 */
	public static final String TIMEZONE = "GMT+8";

	/** 应用的日期格式 */
	public static final String PATTERN_DATE = "yyyy-MM-dd";

	/** 应用的日期时间格式 */
	public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";

	/** session userid 属性key */
	public static final String SESSION_ATTR_NAME_USERID = "userId";

	/** session userRoles 属性key */
	public static final String SESSION_ATTR_NAME_USERROLES = "userRoles";

	/** controller 切入点表达式 */
	public static final String POINTCUT_CONTROLLER = "execution(public * com.*.*.*.web.controller.*.*(..))";

	/** 新增方法名称前缀 */
	public static final String ADD_METHOD_PREFIX = "add";

	/** 应用数据库配置前缀 */
	public static final String DATASOURCE_CONFIG_PREFIX = "spring.datasource";


}
