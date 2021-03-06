package com.ace.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ace.dao.ConfigDAO;
import com.ace.dao.impl.ConfigDAOImpl;
import com.ace.domain.Config;
/*
 * 增加摄像头的servlet
 * */
public class AddCam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ConfigDAO configDAO = new ConfigDAOImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/add_config.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = new String(request.getParameter("name").getBytes("ISO-8859-1"),"utf-8");
		String adress = new String(request.getParameter("adress").getBytes("ISO-8859-1"),"utf-8");
		int isActive = Integer.parseInt(request.getParameter("isActive"));
		int threshold_car = Integer.parseInt(request.getParameter("threshold_car"));
		int threshold_man = Integer.parseInt(request.getParameter("threshold_man"));
		Config con = new Config();
		con.setName(name);
		con.setAdress(adress);
		if(isActive == 0) {
			con.setActive(false);
		}else {
			con.setActive(true);
		}
		con.setThreshold_car(threshold_car);
		con.setThreshold_man(threshold_man);
		configDAO.save(con);
		request.getRequestDispatcher("ConfigData").forward(request, response);
	}

}
