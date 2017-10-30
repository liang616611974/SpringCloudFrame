package com.liangfeng.study.common.helper;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
* @Title: DateHelper.java
* @Description: 时间操作帮助类
* @author Liangfeng
* @date 2016-9-26
* @version 1.0
 */
public class DateHelper {
	
	//时间单位标识 年
	private static final int YEAR = 1;
	//时间单位标识 月
	private static final int MONTH = 2;
	//时间单位标识 日
	private static final int DAY = 3;
	//时间单位标识 小时
	private static final int HOUR = 4;
	//时间单位标识 分
	private static final int MINUTE = 5;
	//时间单位标识 秒
	private static final int SECOND = 6;
	
	//默认的日期格式
	private static final String DATE_PATTERN = "yyyy-MM-dd";
	//默认的日期时间格式
	private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	private DateHelper(){}
	
	/**
	 * 时间对象格式化成字符串
	 * @param date 时间对象
	 * @param pattern 模型，例如：yyyy-MM-dd
	 * @return
	 */
	public static String format(Date date,String pattern){
		if(date==null || StringUtils.isBlank(pattern)){
			return "";
		}
		return DateFormatUtils.format(date, pattern);
	}
	
	/**
	 * 时间对象格式化成默认日期字符串 "yyyy-MM-dd"
	 * @param date 时间对象
	 * @return
	 */
	public static String formatDate(Date date){
		if(date==null){
			return "";
		}
		return DateFormatUtils.format(date, DATE_PATTERN);
	}
	
	/**
	 * 时间对象格式化成默认日期时间字符串 "yyyy-MM-dd HH:mm:ss"
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date){
		if(date==null){
			return "";
		}
		return DateFormatUtils.format(date, DATETIME_PATTERN);
	}
	
	/**
	 * 字符串解析成时间对象
	 * @param dateStr 时间字符串
	 * @param pattern 模型，例如：yyyy-MM-dd
	 * @return
	 */
	public static Date parse(String dateStr,String pattern){
		if(StringUtils.isBlank(dateStr) || StringUtils.isBlank(pattern)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(dateStr,new ParsePosition(0));
	}
	
	/**
	 * 字符串按照默认日期格式"yyyy-MM-dd"解析成时间对象
	 * @param dateStr 时间字符串
	 * @return
	 */
	public static Date parseDate(String dateStr){
		if(StringUtils.isBlank(dateStr)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
		return sdf.parse(dateStr,new ParsePosition(0));
	}
	
	/**
	 * 字符串按照默认日期格式"yyyy-MM-dd HH:mm:dd"解析成时间对象
	 * @param dateStr 时间字符串
	 * @return
	 */
	public static Date parseDateTime(String dateStr){
		if(StringUtils.isBlank(dateStr)){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_PATTERN);
		return sdf.parse(dateStr,new ParsePosition(0));
	}
	
	
	/**
	 * 时间追加
	 * @param date 时间对象
	 * @param year 加多少年 
	 * @param month 加多少月
	 * @param day 加多少天
	 * @return
	 */
	public static Date getAppendDate(Date date,int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (day != 0) {
			cal.add(Calendar.DATE, day);
		}
		if (month != 0) {
			cal.add(Calendar.MONTH, month);
		}
		if (year != 0) {
			cal.add(Calendar.YEAR, year);
		}
		return cal.getTime();
	}
	
	/**
	 * 开始时间和结束时间的间隔数
	 * @param begin 开始时间
	 * @param end 结束时间
	 * @param dateUnit 时间的单位 比如：DateHelper.DAY 相隔多少天
	 * @return
	 */
	public static long getInterval(Date begin, Date end,int dateUnit) {
		//间隔数
		long interval = 0;
		//开始日历
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(begin);
		//结束日历
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);
		switch (dateUnit) {
		case YEAR:
			interval = calEnd.get(Calendar.YEAR) - calBegin.get(Calendar.YEAR);
			break;
		case MONTH:
			interval = (calEnd.get(Calendar.YEAR) - calBegin.get(Calendar.YEAR)) * 12 + (calEnd.get(Calendar.MONTH) - calBegin.get(Calendar.MONTH));
			break;
		case DAY:
			interval = (end.getTime() - begin.getTime()) / (24 * 60 * 60 * 1000);
			break;
		case HOUR:
			interval = (end.getTime() - begin.getTime()) / (60 * 60 * 1000);
			break;
		case MINUTE:
			interval = (end.getTime() - begin.getTime()) / (60 * 1000);
			break;
		case SECOND:
			interval = (end.getTime() - begin.getTime()) / (1000);
			break;
		default:
			break;
		}
		return interval;
	}
	
	/**
	 * 获取当前时间是周几 例如：0是周日--6是周六 
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return dayOfWeek;
	}
	
	/**
	 * 获取某年某月的第一天 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		String dateStr = formatDate(date);
		String[] ds = dateStr.split("-");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.valueOf(ds[0]));
		cal.set(Calendar.MONTH, Integer.valueOf(ds[1]) - 1);
		int minDay = cal.getActualMinimum(Calendar.DATE);
		cal.set(Calendar.DAY_OF_MONTH, minDay);
		return cal.getTime();
	}

	/**
	 * 判断日期是否该日期所在月的第一天 
	 * @param date
	 * @return
	 */
	public static boolean isFirstDayOfMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if(1 == cal.get(Calendar.DAY_OF_MONTH)){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断日期是否是周末
	 * @param date
	 * @return
	 */
	public static boolean isWeekend(Date date){
		int dayOfWeek = getDayOfWeek(date);
		if(dayOfWeek == 0 || dayOfWeek == 6){
			return true;
		}
		return false;
	}
	/**
	 * 得到unix时间戳，可直接存库表用
	 *
	 * @return
	 */
	public static int getUnixTimeStamp() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	/**
	 * 转换成Unix时间戳
	 * @param date
	 * @return
	 */
	public static int getUnixTimeStamp(Date date) {
		return (int) (date.getTime() / 1000);
	}

	/**
	 * 转换成Unix时间戳
	 * @param date
	 * @return
	 */
	public static int getUnixTimeStamp(Calendar date) {
		return (int) (date.getTimeInMillis() / 1000);
	}

	/**
	 * unix时间戳转换成java时间
	 * @param timeStamp
	 * @return
	 */
	public static Date unixTimeStamp2Date(int timeStamp){
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeInMillis(timeStamp*1000l);
		return calendar.getTime();
	}
	public static void main(String[] args) {
		Date date = DateHelper.parse("2016/10/24 12:12:12" , "yyyy-MM-dd");
		System.out.println(date);
	}

}
