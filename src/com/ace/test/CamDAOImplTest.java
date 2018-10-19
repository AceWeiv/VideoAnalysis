package com.ace.test;

import java.util.List;

import org.junit.Test;

import com.ace.dao.AlarmDAO;
import com.ace.dao.CamDAO;
import com.ace.dao.ConfigDAO;
import com.ace.dao.impl.AlarmDAOImpl;
import com.ace.dao.impl.CamDAOImpl;
import com.ace.dao.impl.ConfigDAOImpl;
import com.ace.domain.Alarm;
import com.ace.domain.Cam;

public class CamDAOImplTest {
	
	@Test
	public void test() {
		String a = "2018-07-30 11:00";
		String b = "2018-07-30 11:10";
		CamDAO camDAO = new CamDAOImpl();
		List<Cam> c = camDAO.getLast24(1);
		System.out.println(c.get(0).getScreenshot());
	}
	
	@Test
	public void test1() {
		ConfigDAO c = new ConfigDAOImpl();
		/*Config con = new Config();
		con.setId(6);
		con.setActive(true);
		con.setAdress("1.1.3.6");
		con.setThreshold(80);
		con.setName("华科6摄像头");
		c.update(con);*/
		c.delete(6);
	}
	
	@Test
	public void test2() {
		AlarmDAO a = new AlarmDAOImpl();
		List<Alarm> as = a.getNew(1);
		for(int i = 0; i<as.size(); i++) {
			System.out.println(as.get(i).getId());
		}
	}
}
