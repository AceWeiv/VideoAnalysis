package com.ace.others;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.ace.utils.JdbcUtils;

/*
 * 随机插入数据的线程
 * 1.先读取所有的摄像头的id
 * 2.每隔2秒，对每个摄像头都开始插入
 * */
public class CreateData extends Thread {
	//用此参数决定是否执行循环插入
	private boolean isActive = false;

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public void run() {
		PreparedStatement p = null;
		Connection conn = JdbcUtils.getConnection();
		int cam_id = 1;
		String screenshot = "1.jpg";
		//判断isActive是否为true，是则循环，不是则线程执行完毕
		while (isActive) {
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String newdate = sdf.format(date);
				int car_num = (int) (Math.random() * 100);
				int man_num = (int) (Math.random() * 100);
			try {
				String sql = "INSERT INTO camdatas (cam_id,time,car_num,man_num,screenshot) values(?,?,?,?,?)";
				p = (PreparedStatement) conn.prepareStatement(sql);
				p.setInt(1, cam_id);
				p.setString(2, newdate);
				p.setInt(3, car_num);
				p.setInt(4, man_num);
				p.setString(5, screenshot);
				p.execute();
				System.out.println("cam_id:"+cam_id+",time:" + newdate.toString() + ",car:" + car_num + ",man:" + man_num);
				p = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				this.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		JdbcUtils.releaseConnection(conn);
		System.out.println("自动生成数据线程已结束...");
	}

}
