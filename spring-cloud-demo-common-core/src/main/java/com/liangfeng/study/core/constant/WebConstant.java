package com.liangfeng.study.core.constant;

/**
 * @Title: WebConstant.java
 * @Description: Web层常量
 * @author Liangfeng
 * @date 2017/4/28 0028 上午 10:03
 * @version 1.0
 */
public class WebConstant {

	/**
	 * 请求头
	 */
	public static class RequestHeader{
		/** ajax请求头 */
		public static final String AJAX = "x-requested-with";
		/** ajax请求头value */
		public static final String AJAX_VALUE = "XMLHttpRequest";
		/** refer请求头 */
		public static final String REFERER = "referer";
		/** 文件下载请求头 */
		public static final String FILE_DOWNLOAD = "Content-Disposition";
		/** 浏览器信息请求头 */
		public static final String BROWSER_INFO= "USER-AGENT";
	}

	/**
	 * 请求类型
	 */
	public static class ResponseContentType{
		/** EXCEL ContentType  */
		public static final String EXCEL = "application/vnd.ms-excel";
	}

}
