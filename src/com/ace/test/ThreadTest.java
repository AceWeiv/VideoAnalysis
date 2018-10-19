package com.ace.test;

import org.junit.Test;

import com.ace.others.VideoAnalysis;
import com.ace.utils.ThreadUtils;

public class ThreadTest {
	@Test
	public void threadTest() {
		VideoAnalysis v = new VideoAnalysis();
		v.start();
	}
}
