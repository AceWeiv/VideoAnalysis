package com.ace.test;

import java.text.ParseException;

import org.junit.Test;

import com.ace.utils.TimeUtils;

public class TimeUtilsTest {
	
	@Test
	public void test() throws ParseException {
		TimeUtils t = new TimeUtils();
		String a = "2018-08-08T23:32";
		String b = "2018-09-08 23:32:23";
		System.out.println(t.formatTime(a));
		System.out.println(t.formatTime(b));
	}

}
