package com.ace.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ace.dao.ConfigDAO;
import com.ace.dao.impl.ConfigDAOImpl;
/*
 * 删除摄像头的servlet
 * */
public class DeleteCam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ConfigDAO configDAO = new ConfigDAOImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		configDAO.delete(id);
		request.getRequestDispatcher("ConfigData").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
