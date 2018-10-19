package com.ace.utils;

import java.sql.Connection;
import java.sql.DriverManager;



public class JdbcUtils {
	public static void releaseConnection(Connection connection) {
		try {
			if(connection != null) {
				connection.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static Connection conn = null;
	
	public static Connection getConnection() {
		try {
			String url = "jdbc:mysql://127.0.0.1:3306/video_analysis?useSSL=false";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, "hust403", "root");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
