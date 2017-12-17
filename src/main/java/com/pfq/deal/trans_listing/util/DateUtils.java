package com.pfq.deal.trans_listing.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jemmy.fan on 16/10/20.
 */
public class DateUtils {

	// 取得当时时间的前分钟的时间
	public static String getDateString(Date date) {
		if(date==null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String reStr = sdf.format(date);
		return reStr;
	}
	public static String getDateStringYYYYMMDDHHSSSSS(Date date) {
		if(date==null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String reStr = sdf.format(date);
		return reStr;
	}

	public static Date getTimeDate(String time) {
		if(time==null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(time);
		} catch (ParseException e) {
			throw new RuntimeException("转换格式错误 入参："+time);
		}
	}

}
