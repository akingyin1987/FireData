package com.com.zlcd.firedata.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public final static String dateFormat = "yyyy-MM-dd";
	public final static String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String dateToStr(Date date, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		String str = format.format(date);
		return str;
	}

	public static String longToStr(long milliseconds, String formatStr) {
		if (milliseconds <= 0) {
			return "";
		}
		Date date = new Date(milliseconds);
		return dateToStr(date, formatStr);

	}

	public static String longToStr(Long milliseconds, String formatStr) {
		if (milliseconds == null) {
			return "";
		}
		if (milliseconds <= 0) {
			return "";
		}
		Date date = new Date(milliseconds.longValue());
		return dateToStr(date, formatStr);

	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 *            例如：2012-09-10
	 * @return date
	 */
	public static Date strToDate(String str, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
