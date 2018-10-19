package com.ace.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ace.dao.CamDAO;
import com.ace.dao.impl.CamDAOImpl;
import com.ace.domain.CamK;

import net.sf.json.JSONArray;
/*
 * 给移动端k线图数据的servlet
 * */
public class MobileK extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CamDAO camDAO = new CamDAOImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int cam_id = Integer.parseInt(request.getParameter("cam_id"));	
		int selected_time = Integer.parseInt(request.getParameter("method"));
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		try {
			List<CamK> camK = camDAO.getKList1(cam_id, selected_time, start_time, end_time);
			response.setContentType("text/html; charset=utf-8");
			JSONArray json = JSONArray.fromObject(camK);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
			System.out.println("do...");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
