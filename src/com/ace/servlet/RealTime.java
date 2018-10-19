package com.ace.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ace.dao.CamDAO;
import com.ace.dao.ConfigDAO;
import com.ace.dao.impl.CamDAOImpl;
import com.ace.dao.impl.ConfigDAOImpl;
import com.ace.domain.Cam;
import com.ace.domain.Config;

import net.sf.json.JSONArray;
/*
 * 实时数据的servlet
 * 移动端也可用
 * RealTime.jsp的servlet
 * */
public class RealTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CamDAO camDAO = new CamDAOImpl();
	ConfigDAO configDAO = new ConfigDAOImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Config> cons = configDAO.getAll();
		request.setAttribute("cons", cons);
		request.getRequestDispatcher("/RealTime.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int cam_id = Integer.parseInt(request.getParameter("cam_id"));		
		List<Cam> cams = camDAO.getLast24(cam_id);
		response.setContentType("text/html; charset=utf-8");
		JSONArray json = JSONArray.fromObject(cams);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
