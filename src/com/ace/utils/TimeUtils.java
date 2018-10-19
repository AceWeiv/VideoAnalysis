package com.ace.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
	public String getLastMinute(String lastTime) {
		String arr[]=lastTime.trim().split(":");
		arr[2]="00";
		String time = arr[0]+":"+arr[1]+":"+arr[2];
		return time;
	}
	
	//加减分钟方法
	public String editMinute(String time, int delMin) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar nowTime = Calendar.getInstance();
        Date date = sdf.parse(time);//把字符串转换成时间类型
        nowTime.setTime(date);
        nowTime.add(Calendar.MINUTE, delMin);//要增加什么，在这里写
        String newTime = sdf.format(nowTime.getTime());
		return newTime;
	}
	//加减小时方法
	public String editHour(String time, int delHour) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar nowTime = Calendar.getInstance();
        Date date = sdf.parse(time);//把字符串转换成时间类型
        nowTime.setTime(date);
        nowTime.add(Calendar.HOUR_OF_DAY, delHour);//要增加什么，在这里写
        String newTime = sdf.format(nowTime.getTime());
		return newTime;
	}
	//由于jsp中date-local的格式是yyyy-MM-ddTHH:mm
	//需要将时间转化为标准格式yyyy-MM-dd HH:mm:ss
	public String formatTime(String time) {
		String new_time = time.replace("T", " ");
		new_time = new_time.substring(0,16);
		new_time = new_time + ":00";
		return new_time;
	}
	
}
