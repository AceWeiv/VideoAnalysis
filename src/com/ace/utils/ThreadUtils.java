package com.ace.utils;

import com.ace.others.CreateData;
import com.ace.others.VideoAnalysis;

public class ThreadUtils {
	//判断自动插入线程是否存在，存在则找到那条线程，否则新建自动插入线程
	public CreateData getCreateData() {
		String threadName = "CreateData";
		//通过thread自带的name属性来判断是否存在线程
		for(Thread t : Thread.getAllStackTraces().keySet()) {
			if(t.getName().equals(threadName))
				return (CreateData)t;
		}
		CreateData c = new CreateData();
		c.setName(threadName);
		return c;
	}
	
	public VideoAnalysis getVideoAnalysis() {
		String threadName = "VideoAnalysis";
		for(Thread t : Thread.getAllStackTraces().keySet()) {
			if(t.getName().equals(threadName))
				return (VideoAnalysis)t;
		}
		VideoAnalysis c = new VideoAnalysis();
		c.setName(threadName);
		return c;
	}
	
	public boolean findThreadByName(String threadName) {
		boolean i = false;
		for(Thread t : Thread.getAllStackTraces().keySet()) {
			if(t.getName().equals(threadName))
				i = true;
		}
		return i;
	}
}
