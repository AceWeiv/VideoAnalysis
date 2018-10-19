package com.ace.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.ace.others.VideoAnalysis;
import com.ace.utils.JdbcUtils;

public class JdbcUtilsTest {
	
	@Test
	public void testGetConnection() throws SQLException {
		Connection conn = JdbcUtils.getConnection();
		System.out.println(conn);
	}
	
	@Test
	public void test1(){
		VideoAnalysis v = new VideoAnalysis();
		v.start();
	}
	
}
