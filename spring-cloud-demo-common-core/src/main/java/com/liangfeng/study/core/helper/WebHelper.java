package com.liangfeng.study.core.helper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

/**
 * @Title: WebHelper.java
 * @Description: web操作帮助类
 * @author Liangfeng
 * @date 2017/4/28 0028 上午 10:11
 * @version 1.0
 */
public class WebHelper {
	
	/**
	 * 设置cookie有效期，根据需要自定义
	 */
	private final static int COOKIE_MAX_AGE = 60 * 60 * 24 * 30;

	/**
	 * 浏览器相关设置
	 */
	private static final String ENCODING_UTF8 = "UTF-8";
	private static final String ENCODING_ISO = "iso-8859-1";
	private static final String BROWSER_INFO_HEADER = "USER-AGENT";//"USER-AGENT";
	private static final String USER_AGENT_MSIE = "MSIE";
	private static final String USER_AGENT_TRIDENT = "Trident";
	private static final String FILE_DOWNLOAD_HEADER = "Content-Disposition";//"Content-Disposition";
	private static final String FILE_DOWNLOAD_HEADER_VAL = "attachment;filename=";
	
	private WebHelper(){};

    /**
     *
	 * @return
     */
	public static HttpServletRequest getRequest() {
		HttpServletRequest request = null;
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes instanceof ServletRequestAttributes) {
			ServletRequestAttributes reqAttr = (ServletRequestAttributes) requestAttributes;
			if (null != reqAttr) {
				request = reqAttr.getRequest();
			}
		}
		return request;
	 }

	/**
	 * 获取request的属性值
	 * @param name
	 * @return
	 */
	public static String getRequest(HttpServletRequest request, String name) {
		String value = "";
		if(StringUtils.isBlank(name)){
			return value;
		}
		if (null != request) {
			Object obj = request.getAttribute(name);
			if (null != obj) {
				value = obj.toString();
			}
		}
		return value;
	}



	/**
	 * 获取session的属性值
	 * @param name
	 * @return
	 */
	public static <T> T getSession(HttpServletRequest request, String name) {
		T value = null;
		if(StringUtils.isBlank(name)){
			return value;
		}
		if (null != request) {
			HttpSession session = request.getSession(false);
			if (null != session) {
				Object obj = session.getAttribute(name);
				if (null != obj) {
					value = (T) obj;
				}
			}
		}
		return value;
	}
	
	/**
	 * 获取Cookie 对象
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		Cookie cookies[] = request.getCookies();
		if (name.length() == 0)
			return null;
		Cookie cookie = null;
		for (int i = 0; i < cookies.length; i++) {
			if (!cookies[i].getName().equals(name))
				continue;
			cookie = cookies[i];
			if (request.getServerName().equals(cookie.getDomain())){
				break;
			}
		}

		return cookie;
	}
	
	/**
	 * 获取cookie属性值
	 * @param name
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
        Cookie cookie = getCookie(request,name);
        if (cookie != null) {
            return cookie.getValue();
        } 
        return null;
    }
	
	/**
	 * 设置cookie	
	 * @param response
	 * @param name key
	 * @param value value
	 * @param maxAge 过期时间
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, int maxAge) {
		if (StringUtils.isBlank(name) || StringUtils.isBlank(value)) {
			return;
		}
		Cookie cookie = new Cookie(name, value);
		if (maxAge != 0) {
			cookie.setMaxAge(maxAge);
		} else {
			cookie.setMaxAge(COOKIE_MAX_AGE);
		}
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 设置cookie 默认过期时间一个月
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void setCookie(HttpServletResponse response, String name, String value) {
		setCookie(response, name, value, COOKIE_MAX_AGE);
	}
	
	/**
	 * 删除cookie
	 * @param response
	 * @param cookie
	 */
	public static void removeCookie(HttpServletResponse response, Cookie cookie) {
        if (cookie != null) {
            cookie.setPath("/");
            cookie.setValue("");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }
	
	/**
	 * 获取请求客户端的ip地址
	 * @param request
	 * @return
	 */
	public static String getRequestIp(HttpServletRequest request) {

        String ip = request.getHeader("X-Real-IP");

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Cdn-Src-Ip");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        return ip.split(",")[0];
    }

    /**
	 * 设置下载Http响应信息
	 * @param request
	 * @param response
	 * @param contentType
	 * @param downloadName
	 * @throws Exception
	 */
	public static void setDownloadResponse(HttpServletRequest request, HttpServletResponse response, String contentType,String downloadName) throws Exception {
		// 1..判断浏览器
		String userAgent = request.getHeader(BROWSER_INFO_HEADER);
		/*
		 * 设置不同浏览器中  下载文件的文件名编码
		 * IE11浏览器的user-agent使用MSIE容易识别为firefox  导致出错
		 */
		if (!userAgent.contains(USER_AGENT_MSIE) && !userAgent.contains(USER_AGENT_TRIDENT)) {
			downloadName = new String(downloadName.getBytes(ENCODING_UTF8), ENCODING_ISO);
		} else {
			downloadName = URLEncoder.encode(downloadName, ENCODING_UTF8);
		}

		// 2.设置response
		response.reset();
		request.setCharacterEncoding(ENCODING_UTF8);
		response.setHeader(FILE_DOWNLOAD_HEADER, FILE_DOWNLOAD_HEADER_VAL + downloadName);// 表示以附件形式可下载
		response.setContentType(contentType);// 设置下载格式
	}
}
